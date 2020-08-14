package vip.bblog.cunadmin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="1396513066@qq.com">yy</a>
 * @version 1.0
 * @date 2019年05月10日 10:12
 * @desc SwaggerConfig Swagger2配置
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("Authorization")
                .description("授权码token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build()
        );
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("接口文档 1.0 操作文档</br>" +
                        "<b style='color:red'>" +
                        "其他说明" +
                        "</b>")
                .version("1.0")
                .contact(new Contact("yy", "http://www.blbbl.vip", "1396513066@qq.com"))
                .build();
    }

}
