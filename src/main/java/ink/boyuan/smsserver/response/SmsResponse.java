package ink.boyuan.smsserver.response;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import ink.boyuan.smsserver.config.SmsConfig;
import ink.boyuan.smsserver.function.GenerateCommonResponse;
import ink.boyuan.smsserver.request.SmsRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/4 13:15
 * @description
 **/
@Component
public class SmsResponse implements Serializable {

    /**
     * 时间格式
     */
    private String dateTimeFormatter = "yyyyMMdd";

    private static Log logger = LogFactory.getLog(SmsResponse.class);

    @Autowired
    private SmsConfig smsConfig;

    /**
     * 发送手机短信
     * @param phone
     */
    @Async("asyncPromiseExecutor")
    public void sendSms(String phone,Integer code,String templateCode){
        GenerateCommonResponse.getCommonResponse(phone,code,templateCode,(x,y,z)->{
            DefaultProfile profile = generateDefaultProfile();
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = SmsRequest.generateSmsRequest(x,y,z);
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
     *  JSONObject jsonObject = JSONObject.parseObject(msg);
     *             jsonObject.get("Code")=OK;
     * @return
     */
    @Async("asyncPromiseExecutor")
    public String getMsg(String phone) {
        DefaultProfile profile = generateDefaultProfile();
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", phone);
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SendDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimeFormatter)));
        request.putQueryParameter("PageSize", "1");
        request.putQueryParameter("CurrentPage", "1");
        String msg = null;
        try {
            CommonResponse response = client.getCommonResponse(request);
            msg = response.getData();
            logger.info(msg);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return msg;
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
