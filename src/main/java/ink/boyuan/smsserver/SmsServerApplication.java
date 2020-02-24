package ink.boyuan.smsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 有缘
 */
@SpringBootApplication
@EnableFeignClients
public class SmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsServerApplication.class, args);
    }

}
