package com.aiggo.common.util.smart.validate.match;

import java.util.regex.Pattern;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.RegexpValidate;

/**
 * 正则
 */
public class MatchRegexpValidate extends AbstractMatchValidate<RegexpValidate>{

	@Override
	public boolean validate(RegexpValidate t, 
			String fieldName,
			Object value)
			throws ValidateException {
		
		String defaultMessage = "%s的格式错误";

		if(value == null || !Pattern.matches(t.pattern(), value.toString())) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName)));
			
		}
		
		return false;
	}
}
