package ink.boyuan.smsserver.function;

/**
 * @author 有缘
 * @version 1.0
 * @date 2019/11/6 8:37
 * @description
 **/
@FunctionalInterface
public interface SendSmsFunction<T , U ,R> {
    /**
     * 短信函数接口
     * @param t
     * @param u
     * @param r
     */
    void sendSmsFun(T t,U u,R r);
}
