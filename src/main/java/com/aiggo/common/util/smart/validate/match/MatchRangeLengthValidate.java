package com.aiggo.common.util.smart.validate.match;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.RangeLengthValidate;

/**
 * 字符串长度范围
 * @author lichao
 *
 */
public class MatchRangeLengthValidate extends AbstractMatchValidate<RangeLengthValidate>{

	@Override
	public boolean validate(RangeLengthValidate t,
			String fieldName,
			Object value)
			throws ValidateException {
		
		String defaultMessage = "%s的长度必须在%s和%s之间";

		if(value == null) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			
		}
		
		if(value instanceof String) {
			
			int length = ((String)value).length();
			
			if(!(length >= t.min() && length <= t.max())) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}
			
		} else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "RangeLengthValidate only support String");
			
		}
		return true;
	}
}
