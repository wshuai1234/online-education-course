package net.xdclass.xdvideo.domain;

import java.io.Serializable;

/**
 * function description, util class
 *
 *
 */
public class JsonData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code; // 0 success, 1 processing, -1 failed
	private Object data; // data
	private String msg;// description

	public JsonData() {
	}

	public JsonData(Integer code, Object data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	// success, input data
	public static JsonData buildSuccess() {
		return new JsonData(0, null, null);
	}

	// success, input data
	public static JsonData buildSuccess(Object data) {
		return new JsonData(0, data, null);
	}

	// failed, input description info
	public static JsonData buildError(String msg) {
		return new JsonData(-1, null, msg);
	}

	// failed, input description info, status code
	public static JsonData buildError(String msg, Integer code) {
		return new JsonData(code, null, msg);
	}

	// success, input data and description info
	public static JsonData buildSuccess(Object data, String msg) {
		return new JsonData(0, data, msg);
	}

	// success, input data and status code
	public static JsonData buildSuccess(Object data, int code) {
		return new JsonData(code, data, null);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg
				+ "]";
	}

}
