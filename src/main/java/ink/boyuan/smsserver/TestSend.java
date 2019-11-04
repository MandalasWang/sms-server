package ink.boyuan.smsserver;

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

    @Autowired
    private SmsResponse smsResponse;

    @RequestMapping(value = "send",method = RequestMethod.GET)
    public void sendSms(@RequestParam(value = "phone")String phone){
        smsResponse.sendSms(phone);
    }
}
