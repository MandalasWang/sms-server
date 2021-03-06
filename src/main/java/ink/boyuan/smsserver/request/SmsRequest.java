package ink.boyuan.smsserver.request;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.http.MethodType;
import ink.boyuan.smsserver.constant.ResponseConstant;
import ink.boyuan.smsserver.function.GenerateCommonRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/4 9:58
 * @description
 **/
@Component
public class SmsRequest {


    private static Log logger= LogFactory.getLog(SmsRequest.class);

    public static  CommonRequest generateSmsRequest(String phone,Integer verificationCode,String templateCode){

        return GenerateCommonRequest.generateCommonRequest(templateCode,verificationCode,(model, code)->{
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers",phone );
            request.putQueryParameter("SignName", ResponseConstant.SIGN_NAME);
            request.putQueryParameter("TemplateCode", model);
            request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
            logger.info(request);
            return request;
        });
    }

}
