package vip.bblog.cunadmin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2019年10月15日 10:22
 * @desc MybatisPlusConfig mp分页
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }
}
