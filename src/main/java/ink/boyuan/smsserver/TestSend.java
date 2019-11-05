package ink.boyuan.smsserver;

import com.alibaba.fastjson.JSONObject;
import ink.boyuan.smsserver.constant.ResponseConstant;
import ink.boyuan.smsserver.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/4 13:27
 * @description
 **/
@RestController
@RequestMapping("/")
public class TestSend {

    /**
     * 消息状态码
     */
    private final static String SEND_SUCCESS ="OK";

    @Autowired
    private SmsResponse smsResponse;

    @RequestMapping(value = "send",method = RequestMethod.GET)
    public String sendSms(@RequestParam(value = "phone")String phone){
        JSONObject jsonObject = JSONObject.parseObject(smsResponse.getMsg(phone));
        smsResponse.sendSms(phone);
        if(SEND_SUCCESS.equals(jsonObject.get(ResponseConstant.CODE))){
            return "恭喜你，短信发送成功！";
        }
        return "短信发送失败！";
    }
}
