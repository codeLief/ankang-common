package com.aiggo.common.util.exception;

/**
 * 
 * @Description: 业务异常基类，所有业务异常都必须继承于此异常 
 * @author: ankang
 * @date: 2016-5-24 上午11:14:07
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * 数据库操作,insert返回0
	 */
	public static final BaseException DB_INSERT_RESULT_0 = new BaseException(90040001, "数据库操作,insert返回0");

	/**
	 * 数据库操作,update返回0
	 */
	public static final BaseException DB_UPDATE_RESULT_0 = new BaseException(90040002, "数据库操作,update返回0");

	/**
	 * 数据库操作,selectOne返回null
	 */
	public static final BaseException DB_SELECTONE_IS_NULL = new BaseException(90040003, "数据库操作,selectOne返回null");

	/**
	 * 数据库操作,list返回null
	 */
	public static final BaseException DB_LIST_IS_NULL = new BaseException(90040004, "数据库操作,list返回null");

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public BaseException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public BaseException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public BaseException newInstance(String msgFormat, Object... args) {
		return new BaseException(this.code, msgFormat, args);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message) {
		super(message);
	}
}
