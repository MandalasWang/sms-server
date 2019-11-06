package ink.boyuan.smsserver.httpmsg;

/**
 * @author 有缘
 */
public enum RetEnum {


    /**
     * 请求成功
     */
    REQUEST_SUCCESS("200","请求成功！"),
    /**
     * 请求失败
     */
    REQUEST_ERROR("400","请求失败！");

    private String code;
    /**
     * 用户支付信息
     */
    private String msg;
    RetEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取用户状态码
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置用户支付状态码信息
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取用户信息
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置信息
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
