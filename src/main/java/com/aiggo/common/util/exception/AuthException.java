/**
 * 
 */
package com.aiggo.common.util.exception;

/**
 * 
 * @Description:  认证异常 
 * @author: qd-ankang
 * @date: 2016-8-24 下午3:38:26
 */
public class AuthException extends Exception {
	
	private static final long serialVersionUID = 4529606885256651455L;

	public AuthException(){
		super();
	}
	
	public AuthException(String message) {
		super(message);
	}

	public AuthException(Throwable cause) {
		super(cause);
	}

	public AuthException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
