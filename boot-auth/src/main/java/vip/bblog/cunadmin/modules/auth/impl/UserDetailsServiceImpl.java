package vip.bblog.cunadmin.modules.auth.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.modules.system.service.SysUserService;

/**
 * spring security登陆处理<br>
 *
 * @author yy
 */
@Service
@Primary
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("请输入用户名");
        }
        LoginUser loginUser = sysUserService.getLoginUserByUserName(username);
        if (null == loginUser) {
            throw new UsernameNotFoundException("账户不存在");
        }
        if (!loginUser.isEnabled()) {
            throw new LockedException("账户已被禁用");
        }
        return loginUser;
    }

}
