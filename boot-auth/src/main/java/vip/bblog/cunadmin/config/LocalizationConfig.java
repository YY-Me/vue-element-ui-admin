package vip.bblog.cunadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author <a href="1396513066@qq.com">Yu Yong</a>
 * @version 1.0
 * @date 2019年11月04日 22:07
 * @desc LocalizationConfig 错误国际化配置
 */
@Configuration
public class LocalizationConfig {
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:messages");
        return source;
    }
}
