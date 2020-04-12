package net.xdclass.xdvideo.XdException;


/**
 * customized exception class
 */
public class XdException extends RuntimeException {


    /**
     * status code
     */
    private Integer code;
    /**
     * exception message
     */
    private String msg;




    public XdException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
