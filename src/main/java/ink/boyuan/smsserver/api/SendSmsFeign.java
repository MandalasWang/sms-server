package ink.boyuan.smsserver.api;

import ink.boyuan.smsserver.common.RetResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 有缘
 * @version 1.0
 * @date 2019/11/6 9:22
 * @description
 **/
public interface SendSmsFeign {
    /**
     * 登录验证短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "sms/api/send",method = RequestMethod.GET)
    RetResult sendLoginSms(@RequestParam(value = "phone")String phone);

    /**
     * 注册验证短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "sms/api/sendRegister",method = RequestMethod.GET)
    RetResult sendRegisterSms(@RequestParam(value = "phone")String phone);

    /**
     * 修改验证短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "sms/api/sendModify",method = RequestMethod.GET)
    RetResult sendModifySms(@RequestParam(value = "phone")String phone);


}
