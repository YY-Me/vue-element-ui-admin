package vip.bblog.cunadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author yy
 * @version 1.0
 * @date 2020-1-23
 */
@EnableAsync
@SpringBootApplication
public class BootAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootAuthApplication.class, args);
    }

}
