package ink.boyuan.smsserver.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author wyy
 * @version 1.0
 * @date 2019/12/17 10:21
 * @description
 **/
@FeignClient(name = "amq-server")
public interface AmqProducer {



    /**
     * 生产消息队列
     * @param msg
     * @param name
     * @return
     */
    @RequestMapping(path = "/amq/product/sendQue")
    public String sendMsg(@RequestParam(value = "msg") String msg,@RequestParam(value = "name") String name);

    /**
     * 发布订阅
     * @param topic
     * @return
     */
    @RequestMapping(value = "/amq/product/sendTopic")
    public String sendTopic(@RequestBody String topic);

}
