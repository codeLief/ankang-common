package com.aiggo.common.util.smart.validate.match;

import java.lang.annotation.Annotation;

import org.apache.commons.lang.StringUtils;

import com.aiggo.common.util.exception.ValidateException;

/**
 * @param <T>
 */
public abstract class AbstractMatchValidate<T extends Annotation> {

	public abstract boolean validate(T t, String fieldName, Object value) throws ValidateException;
	
	/**
	 * 获取字段名称
	 * @param name
	 * @param fieldName
	 * @return
	 */
	protected String getName(String name, String fieldName) {
		
		if(StringUtils.isEmpty(name)) {
			
			return fieldName;
		}
		
		return name;
	}
	
	/**
	 * 获取提示信息
	 * @param definedMessage
	 * @param defaultMessage
	 * @param defaultMessageArgus
	 * @return
	 */
	protected String getMessage(
			String definedMessage,
			String defaultMessage,
			Object... defaultMessageArgus) {
		
		if(StringUtils.isEmpty(definedMessage)) {
			
			return String.format(defaultMessage, defaultMessageArgus);
			
		}
		
		return definedMessage;
	}
}
