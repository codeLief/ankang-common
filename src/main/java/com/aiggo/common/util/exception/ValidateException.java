package com.aiggo.common.util.exception;

/**
 * 
 * @Description: validate
 * @author: ankang
 * @date: 2015-5-21 下午3:45:02
 */
public class ValidateException extends BaseException {

	private static final long serialVersionUID = 7122658111260716065L;
	
	/**
	 * 数据校验
	 */
	public static final ValidateException CHECK_FAIL = new ValidateException(9000001, "数据检验失败");
	
	public ValidateException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public ValidateException(int code, Throwable cause, String msgFormat, Object... args) {
		super(cause);
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}
	
	public ValidateException newInstance(String msgFormat, Object... args) {
		return new ValidateException(this.code, this.msg + ":" + msgFormat, args);
	}
	
	public ValidateException newInstance(Throwable cause, String msgFormat, Object... args) {
		return new ValidateException(code, cause, msgFormat, args);
	}
	
}
