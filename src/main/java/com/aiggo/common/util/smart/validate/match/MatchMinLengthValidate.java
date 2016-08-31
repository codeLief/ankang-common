package com.aiggo.common.util.smart.validate.match;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.MinLengthValidate;

/**
 * 最小字符串长度
 *
 */
public class MatchMinLengthValidate extends AbstractMatchValidate<MinLengthValidate>{

	@Override
	public boolean validate(MinLengthValidate t, 
			String fieldName,
			Object value)
			throws ValidateException {
		
		String defaultMessage = "%s的长度不能小于%s";

		if(value == null) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			
		}
		
		if(value instanceof String) {
			
			int minLength = t.length();
			
			if(((String)value).length() < minLength) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			}
			
		}
		else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "MinLengthValidate only support String");
			
		}
		return true;
	}
	
}
