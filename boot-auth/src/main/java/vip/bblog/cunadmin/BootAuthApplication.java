package vip.bblog.cunadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author yy
 * @version 1.0
 * @date 2020-1-23
 */
@EnableAsync
@SpringBootApplication
@MapperScan(basePackages = {"vip.bblog.cunadmin.modules.*.mapper"})
@EnableBinding({OrderStreamClient.class})
public class BootAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAuthApplication.class, args);
    }

}
