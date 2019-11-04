package ink.boyuan.smsserver.function;

import com.aliyuncs.CommonRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

/**
 * @author 有缘
 * @version 1.0
 * @date 2019/11/4 10:02
 * @description
 **/
@Component
public class GenerateCommonRequest {

    /**
     * 生成request方法函数
     * @param templateCode
     * @param code
     * @param biFunction
     * @return
     */
    public static CommonRequest generateCommonRequest(String templateCode, int code, BiFunction<String,Integer,CommonRequest> biFunction){
        return biFunction.apply(templateCode,code);
    }

}
