package vip.bblog.cunadmin.modules.auth.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vip.bblog.cunadmin.common.entity.CustomToken;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.modules.auth.CustomTokenService;
import vip.bblog.cunadmin.util.JwtUtils;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yy
 */
@Primary
@Service
@Slf4j
public class CustomTokenServiceJwtImpl implements CustomTokenService {

    @Value("${token.expire.seconds:1296000}")
    private Long expireSeconds;

    @Value("${token.max-limit:20}")
    private Integer maxTokenLimit;

    @Autowired
    private RedisTemplate<String, LoginUser> redisTemplate;

    @Override
    public CustomToken saveToken(LoginUser loginUser) {
        String uuid = UUID.randomUUID().toString();
        CustomToken customToken = JwtUtils.createToken(loginUser.getUsername(), uuid, expireSeconds);
        loginUser.setCustomToken(customToken);
        loginUser.setToken(uuid);
        cacheLoginUserLimit(loginUser);
        return loginUser.getCustomToken();
    }

    /**
     * 缓存登录信息，待登录次数限制
     */
    private void cacheLoginUserLimit(LoginUser loginUser) {
        String cacheKey = String.format("%s:%s", loginUser.getUsername(), loginUser.getToken());
        Set<String> keys = redisTemplate.keys(this.getLoginCacheKey(String.format("%s:*", loginUser.getUsername())));
        if (null != keys && keys.size() > maxTokenLimit) {
            String tempKey = keys.iterator().next();
            redisTemplate.boundValueOps(tempKey).set(loginUser, expireSeconds, TimeUnit.SECONDS);
            LoginUser tempInfo = redisTemplate.boundValueOps(tempKey).get();
            if (null != tempInfo) {
                loginUser.setCustomToken(tempInfo.getCustomToken());
            }
        } else {
            redisTemplate.boundValueOps(this.getLoginCacheKey(cacheKey)).set(loginUser, expireSeconds, TimeUnit.SECONDS);
        }
    }

    /**
     * 缓存登录信息
     */
    private void cacheLoginUser(LoginUser loginUser) {
        String cacheKey = String.format("%s:%s", loginUser.getUsername(), loginUser.getToken());
        redisTemplate.boundValueOps(this.getLoginCacheKey(cacheKey)).set(loginUser, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 更新缓存的用户信息
     * 这里相当于续期
     */
    @Override
    public void renew(LoginUser loginUser) {
        cacheLoginUser(loginUser);
    }

    /**
     * 根据旧的token获取新的token
     *
     * @param loginUser 登录信息
     * @return R
     */
    @Override
    public CustomToken refresh(LoginUser loginUser) {
        String cacheKey = String.format("%s:%s", loginUser.getUsername(), loginUser.getToken());
        LoginUser oldInfo = redisTemplate.boundValueOps(this.getLoginCacheKey(cacheKey)).get();
        Assert.notNull(oldInfo, "获取登录信息失败，请重新登录");
        String uuid = UUID.randomUUID().toString();
        CustomToken customToken = JwtUtils.createToken(loginUser.getUsername(), uuid, expireSeconds);
        loginUser.setCustomToken(customToken);
        redisTemplate.boundValueOps(this.getLoginCacheKey(cacheKey)).set(loginUser, expireSeconds, TimeUnit.SECONDS);
        //删除旧的token

        return customToken;
    }

    @Override
    public LoginUser getLoginUser(String jwtToken) {
        String uuid = JwtUtils.getuuid(jwtToken);
        if (uuid != null) {
            return redisTemplate.boundValueOps(this.getLoginCacheKey(uuid)).get();
        }
        return null;
    }

    @Override
    public void deleteToken(String jwtToken) {
        String uuid = JwtUtils.getuuid(jwtToken);
        if (StringUtils.isNotBlank(uuid)) {
            String key = this.getLoginCacheKey(uuid);
            LoginUser loginUser = redisTemplate.opsForValue().get(key);
            if (loginUser != null) {
                redisTemplate.delete(key);
            }
        }
    }

    /**
     * 获取登录信息缓存key
     */
    private String getLoginCacheKey(String key) {
        return String.format("access_token:%s", key);
    }

}
