package vip.bblog.cunadmin.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vip.bblog.cunadmin.common.entity.CustomToken;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.modules.auth.CustomTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Token过滤器
 *
 * @author yy
 */
@Component
public class CustomTokenFilter extends OncePerRequestFilter {

    public static final String TOKEN_KEY = "Authorization";

    private static final Long DAY = 24 * 3600 * 1000L;

    @Autowired
    private CustomTokenService customTokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getAccessToken(request);
        if (StringUtils.isNotBlank(token)) {
            LoginUser loginUser = customTokenService.getLoginUser(token);
            if (loginUser != null) {
                loginUser = checkLoginTime(loginUser);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser,
                        null, loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 校验时间<br>
     * 过期时间与当前时间对比，临近过期10分钟内的话，自动刷新缓存
     */
    private LoginUser checkLoginTime(LoginUser loginUser) {
        CustomToken customToken = loginUser.getCustomToken();
        Date expireTime = customToken.getExpires_in();
        long currentTime = System.currentTimeMillis();
        if (expireTime.getTime() - currentTime <= DAY) {
            loginUser = (LoginUser) userDetailsService.loadUserByUsername(loginUser.getUsername());
            loginUser.setCustomToken(customToken);
            customTokenService.renew(loginUser);
        }
        return loginUser;
    }

    /**
     * 根据参数或者header获取token
     */
    public static String getAccessToken(HttpServletRequest request) {
        String token = request.getParameter(TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            token = request.getHeader(TOKEN_KEY);
        }
        return token;
    }

}
