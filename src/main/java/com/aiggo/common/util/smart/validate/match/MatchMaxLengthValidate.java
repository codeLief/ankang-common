package com.aiggo.common.util.smart.validate.match;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.MaxLengthValidate;


/**
 * 
 * @Description:  * 字符串最大长度 Null满足条件 
 * @date: 2016-5-24 上午11:17:28
 */
public class MatchMaxLengthValidate extends AbstractMatchValidate<MaxLengthValidate>{

	@Override
	public boolean validate(MaxLengthValidate t, 
			String fieldName,
			Object value)
			throws ValidateException {
		
		if(value == null) {
			
			return true;
		}
		
		String defaultMessage = "%s的长度不能大于%s";

		if(value instanceof String) {
			
			int maxLength = t.length();
			
			if(((String)value).length() > maxLength) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.length()));
			}
			
		}
		else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "MaxLengthValidate only support String");
			
		}
		return true;
	}
	
}
