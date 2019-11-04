package ink.boyuan.smsserver.response;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import ink.boyuan.smsserver.config.SmsConfig;
import ink.boyuan.smsserver.function.GenerateCommonResponse;
import ink.boyuan.smsserver.request.SmsRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/4 13:15
 * @description
 **/
@Component
public class SmsResponse implements Serializable {


    private static Log logger = LogFactory.getLog(SmsResponse.class);

    @Autowired
    private SmsConfig smsConfig;
    /**
     * 发送手机短信
     * @param phone
     */
    public void sendSms(String phone){
        GenerateCommonResponse.getCommonResponse(phone,x->{
            DefaultProfile profile = generateDefaultProfile();
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = SmsRequest.generateSmsRequest(phone);
            try {
                CommonResponse response = client.getCommonResponse(request);
                logger.info(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }

        });

    }


    /**
     * 工厂模式创建 DefaultProfile 实例
     * @return
     */
    private  DefaultProfile generateDefaultProfile(){

        Supplier supplier = ()->{
            return DefaultProfile.getProfile("cn-hangzhou",  smsConfig.accessKeyID, smsConfig.AccessKeySecret);
        };
        return (DefaultProfile)supplier.get();
    }

}
