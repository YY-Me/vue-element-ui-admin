package vip.bblog.cunadmin.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import vip.bblog.cunadmin.common.entity.BaseResult;
import vip.bblog.cunadmin.common.entity.CustomToken;
import vip.bblog.cunadmin.common.entity.LoginUser;
import vip.bblog.cunadmin.filter.CustomTokenFilter;
import vip.bblog.cunadmin.modules.auth.CustomTokenService;
import vip.bblog.cunadmin.util.ResponseUtils;

/**
 * spring security处理器
 */
@Configuration
@Slf4j
public class SecurityHandlerConfig {

    @Autowired
    private CustomTokenService customTokenService;

    /**
     * 登陆成功，返回Token
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            CustomToken loginInfo = customTokenService.saveToken(loginUser);
            ResponseUtils.responseJson(response, HttpStatus.OK.value(), BaseResult.success(loginInfo));
        };
    }

    /**
     * 登陆失败
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            String msg;
            if (exception instanceof BadCredentialsException) {
                msg = "账号或密码错误";
            } else {
                msg = exception.getMessage();
            }
            BaseResult<String> info = BaseResult.build(HttpStatus.BAD_REQUEST.value(), msg);
            ResponseUtils.responseJson(response, HttpStatus.OK.value(), info);
        };

    }

    /**
     * 未登录，返回401
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            ResponseUtils.responseJson(response, HttpStatus.UNAUTHORIZED.value(),
                    BaseResult.build(HttpStatus.UNAUTHORIZED.value(), "凭证已过期"));
        };
    }

    /**
     * 退出处理
     */
    @Bean
    public LogoutSuccessHandler logoutSussHandler() {
        return (request, response, authentication) -> {
            String token = CustomTokenFilter.getAccessToken(request);
            customTokenService.deleteToken(token);
            ResponseUtils.responseJson(response, HttpStatus.OK.value(),
                    BaseResult.build(HttpStatus.OK.value(), "退出成功"));
        };
    }

}
