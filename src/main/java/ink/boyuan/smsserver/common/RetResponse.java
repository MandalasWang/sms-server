package ink.boyuan.smsserver.common;

import ink.boyuan.smsserver.httpmsg.RetEnum;

/**
 * @author 有缘
 */
public class RetResponse {
    private final static String SUCCESS = "success";
    private final static String  FAILED = "failed";

    public static <T> RetResult<T> makeOKRsp() {
        return new RetResult<T>().setCode(RetEnum.REQUEST_SUCCESS).setMsg(SUCCESS);
    }
    public static <T> RetResult<T> makeOKRsp(T data) {
        return new RetResult<T>().setCode(RetEnum.REQUEST_SUCCESS).setMsg(SUCCESS).setData(data);
    }
    public static <T> RetResult<T> makeOKRsp(String msg) {
        return new RetResult<T>().setCode(RetEnum.REQUEST_SUCCESS).setMsg(msg);
    }
    public static <T> RetResult<T> makeErrRsp() {
        return new RetResult<T>().setCode(RetEnum.REQUEST_ERROR).setMsg(FAILED);
    }
    public static <T> RetResult<T> makeErrRsp(String message) {
        return new RetResult<T>().setCode(RetEnum.REQUEST_ERROR).setMsg(message);
    }
    public static <T> RetResult<T> makeRsp(String code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetResult<T> makeRsp(String code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
