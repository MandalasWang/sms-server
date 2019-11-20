package ink.boyuan.smsserver;

import com.alibaba.fastjson.JSONObject;
import ink.boyuan.smsserver.api.SendSmsFeign;
import ink.boyuan.smsserver.common.RetResponse;
import ink.boyuan.smsserver.common.RetResult;
import ink.boyuan.smsserver.constant.ResponseConstant;
import ink.boyuan.smsserver.response.SmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
@RequestMapping("/api")
public class TestSend implements SendSmsFeign {

    /**
     * 消息状态码
     */
    private final static String SEND_SUCCESS ="OK";

    @Autowired
    private SmsResponse smsResponse;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @RequestMapping(value = "send",method = RequestMethod.GET)
    public RetResult sendLoginSms(@RequestParam(value = "phone")String phone){
        JSONObject jsonObject = JSONObject.parseObject(smsResponse.getMsg(phone));
        Integer code = (int)Math.floor((Math.random()*9+1)*100000);
        smsResponse.sendSms(phone,code,ResponseConstant.LOGIN_TEMPLATE_CODE);
        if(SEND_SUCCESS.equals(jsonObject.get(ResponseConstant.CODE))){
            stringRedisTemplate.opsForValue().set(phone,code.toString());
            return RetResponse.makeRsp("200","短信发送成功",code);
        }
        return RetResponse.makeErrRsp();
    }

    @Override
    @RequestMapping(value = "sendRegister",method = RequestMethod.GET)
    public RetResult sendRegisterSms(@RequestParam(value = "phone")String phone){
        JSONObject jsonObject = JSONObject.parseObject(smsResponse.getMsg(phone));
        Integer code = (int)Math.floor(Math.random()*1000000);
        smsResponse.sendSms(phone,code,ResponseConstant.REGISTER_TEMPLATE_CODE);
        if(SEND_SUCCESS.equals(jsonObject.get(ResponseConstant.CODE))){
            stringRedisTemplate.opsForValue().set(phone,code.toString());
            return RetResponse.makeRsp("200","短信发送成功",code);
        }
        return RetResponse.makeErrRsp();
    }

    @Override
    @RequestMapping(value = "sendModify",method = RequestMethod.GET)
    public RetResult sendModifySms(@RequestParam(value = "phone")String phone){
        JSONObject jsonObject = JSONObject.parseObject(smsResponse.getMsg(phone));
        Integer code = (int)Math.floor(Math.random()*1000000);
        smsResponse.sendSms(phone,code,ResponseConstant.MODIFIED_TEMPLATE_CODE);
        if(SEND_SUCCESS.equals(jsonObject.get(ResponseConstant.CODE))){
            stringRedisTemplate.opsForValue().set(phone,code.toString());
            return RetResponse.makeRsp("200","短信发送成功",code);
        }
        return RetResponse.makeErrRsp();
    }

}
