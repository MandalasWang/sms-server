package ink.boyuan.smsserver.request;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.http.MethodType;
import ink.boyuan.smsserver.function.GenerateCommonRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/4 9:58
 * @description
 **/
@Component
public class SmsRequest {

    /**
     * 生成随机6位数
     */
    private static int CODE = (int)((Math.random()*9+1)*100000);

    private static Log logger= LogFactory.getLog(SmsRequest.class);

    private static String TEMPLATE_CODE = "SMS_174987614";

    public static  CommonRequest generateSmsRequest(String phone){

        return GenerateCommonRequest.generateCommonRequest(TEMPLATE_CODE,CODE,(model, code)->{
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers",phone );
            request.putQueryParameter("SignName", "博渊学客");
            request.putQueryParameter("TemplateCode", model);
            request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
            logger.info(request);
            return request;
        });
    }

}
