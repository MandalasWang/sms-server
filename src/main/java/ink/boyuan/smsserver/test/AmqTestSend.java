package ink.boyuan.smsserver.test;

import com.alibaba.fastjson.JSONObject;
import ink.boyuan.smsserver.common.RetResponse;
import ink.boyuan.smsserver.common.RetResult;
import ink.boyuan.smsserver.constant.ResponseConstant;
import ink.boyuan.smsserver.httpmsg.RetEnum;
import ink.boyuan.smsserver.product.AmqProducer;
import ink.boyuan.smsserver.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyy
 * @version 1.0
 * @date 2019/12/17 10:29
 * @description
 **/
@RestController
@RequestMapping(value = "/amq")
public class AmqTestSend {

    /**
     * 消息状态码
     */
    private final static String SEND_SUCCESS ="OK";

    @Autowired
    private SmsResponse smsResponse;

    @Autowired
    private AmqProducer amqProducer;




    @RequestMapping(value = "/sendLoginToAmq",method = RequestMethod.GET)
    public RetResult sendLoginSms(@RequestParam(value = "phone",required = true) String phone){
        JSONObject jsonObject = JSONObject.parseObject(smsResponse.getMsg(phone));
        Integer code = (int)Math.floor((Math.random()*9+1)*100000);
        smsResponse.sendSms(phone,code, ResponseConstant.LOGIN_TEMPLATE_CODE);
        return getRetResult(jsonObject, code);
    }


    @RequestMapping(value = "/sendRegister",method = RequestMethod.GET)
    public RetResult sendRegisterSms(@RequestParam(value = "phone")String phone){
        JSONObject jsonObject = JSONObject.parseObject(smsResponse.getMsg(phone));
        Integer code = (int)Math.floor(Math.random()*1000000);
        smsResponse.sendSms(phone,code,ResponseConstant.REGISTER_TEMPLATE_CODE);
        return getRetResult(jsonObject, code);
    }


    @RequestMapping(value = "sendModify",method = RequestMethod.GET)
    public RetResult sendModifySms(@RequestParam(value = "phone")String phone){
        JSONObject jsonObject = JSONObject.parseObject(smsResponse.getMsg(phone));
        Integer code = (int)Math.floor(Math.random()*1000000);
        smsResponse.sendSms(phone,code,ResponseConstant.MODIFIED_TEMPLATE_CODE);
        return getRetResult(jsonObject, code);
    }

    private RetResult getRetResult(JSONObject jsonObject, Integer code) {
        if (SEND_SUCCESS.equals(jsonObject.get(ResponseConstant.CODE))) {
            String sendMsg = amqProducer.sendMsg(code.toString(), "sms.queue");
            JSONObject parseObject = JSONObject.parseObject(sendMsg);
            Object code1 = parseObject.get("code");
            if (RetEnum.REQUEST_SUCCESS.equals(code1)) {
                return RetResponse.makeRsp("200", "短信发送成功", "sms.queue");
            }
            return RetResponse.makeErrRsp();
        }
        return RetResponse.makeErrRsp();
    }
}
