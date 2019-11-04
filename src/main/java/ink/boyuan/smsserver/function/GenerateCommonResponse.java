package ink.boyuan.smsserver.function;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;


/**
 * @author wyy
 * @version 1.0
 * @date 2019/11/4 13:02
 * @description
 **/
@Component
public class GenerateCommonResponse {

    private static Log logger = LogFactory.getLog(GenerateCommonResponse.class);


    public static void getCommonResponse(String phone, Consumer<String> consumer){
        logger.info(consumer);
        consumer.accept(phone);
    }


}
