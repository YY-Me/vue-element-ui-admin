package vip.bblog.cunadmin.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vip.bblog.cunadmin.filter.CustomTokenFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * spring security配置
 *
 * @author yy
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomTokenFilter customTokenFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //关闭默认的session存储
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/test/**", "/auth/**", "/doc.html", "/api-docs-ext/**", "/webjars/**",
                        "/swagger-resources/**", "/v2/**", "/swagger-ui.html", "**.js", "**.css").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.logout().logoutUrl("/auth/logout").logoutSuccessHandler(logoutSuccessHandler);
        //redis存储token过滤器
        http.addFilterBefore(customTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter() {
            @Override
            public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
                if (!"POST".equals(request.getMethod())) {
                    throw new AuthenticationServiceException(
                            "Authentication method not supported: " + request.getMethod());
                }
                if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
                    UsernamePasswordAuthenticationToken authRequest;
                    try (InputStream is = request.getInputStream()) {
                        Map authenticationBean = objectMapper.readValue(is, Map.class);
                        String username = MapUtils.getString(authenticationBean, "username", "");
                        String password = MapUtils.getString(authenticationBean, "password", "");
                        authRequest = new UsernamePasswordAuthenticationToken(username, password);
                    } catch (IOException e) {
                        authRequest = new UsernamePasswordAuthenticationToken("", "");
                    }
                    setDetails(request, authRequest);
                    Authentication authenticate = getAuthenticationManager().authenticate(authRequest);
                    return authenticate;
                } else {
                    return super.attemptAuthentication(request, response);
                }
            }
        };
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
