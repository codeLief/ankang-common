package com.aiggo.common.util.smart.validate.match;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.NotNullValidate;

/**
 * 非空验证
 *
 */
public class MatchNotNullValidate extends AbstractMatchValidate<NotNullValidate>{

	@Override
	public boolean validate(NotNullValidate t,
			String fieldName,
			Object value) throws ValidateException {
		
		String defaultMessage = "%s为必填项";

		if(value == null || value.toString().trim().length() == 0) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName)));
			
		}
		
		return true;
	}
}
