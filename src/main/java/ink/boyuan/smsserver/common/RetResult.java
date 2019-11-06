package ink.boyuan.smsserver.common;


import ink.boyuan.smsserver.httpmsg.RetEnum;

/**
 * @author 有缘
 * @param <T>
 */
public class RetResult<T> {
    public String code;

    private String msg;

    private T data;

    public RetResult<T> setCode(RetEnum retCode) {
        this.code = retCode.getCode();
        return this;
    }

    public String getCode() {
        return code;
    }

    public RetResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
