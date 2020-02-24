package ink.boyuan.smsserver.api;

import ink.boyuan.smsserver.common.RetResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 * @author 有缘
 * @version 1.0
 * @date 2019/11/6 9:22
 * @description
 **/
@Validated(Default.class)
public interface SendSmsFeign {
    /**
     * 登录验证短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "sms/api/send",method = RequestMethod.GET)
    RetResult sendLoginSms(@RequestParam(value = "phone",required = true)
                           @Validated @Pattern(regexp = "/^[1]([3-9])[0-9]{9}$/",message = "请输入正确的手机号码")
                           String phone);

    /**
     * 注册验证短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "sms/api/sendRegister",method = RequestMethod.GET)
    RetResult sendRegisterSms(@RequestParam(value = "phone",required = true)
                              @Validated @Pattern(regexp = "/^[1]([3-9])[0-9]{9}$/",message = "请输入正确的手机号码")
                             String phone);

    /**
     * 修改验证短信
     * @param phone
     * @return
     */
    @RequestMapping(value = "sms/api/sendModify",method = RequestMethod.GET)
    RetResult sendModifySms(@RequestParam(value = "phone",required = true)
                            @Validated @Pattern(regexp = "/^[1]([3-9])[0-9]{9}$/",message = "请输入正确的手机号码")
                            String phone);


}
