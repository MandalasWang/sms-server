package ink.boyuan.smsserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/9/29 14:56
 * @description  短信配置类 用于配置ak
 **/
@Component
@PropertySource(value="classpath:application.yml")
@ConfigurationProperties(prefix = "aliyunsms")
public class SmsConfig {

    @Value("${AccessKeyID}")
    public  String accessKeyID;

    @Value("${AccessKeySecret}")
    public  String AccessKeySecret;

    public String getAccessKeyID() {
        return accessKeyID;
    }


    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }


    public void setAccessKeySecret(String accessKeySecret) {
        this.AccessKeySecret = accessKeySecret;
    }
}
