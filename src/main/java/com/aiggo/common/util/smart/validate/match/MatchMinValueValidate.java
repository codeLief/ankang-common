package com.aiggo.common.util.smart.validate.match;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.MinValueValidate;

/**
 * 最小值
 */
public class MatchMinValueValidate extends AbstractMatchValidate<MinValueValidate>{

	@Override
	public boolean validate(MinValueValidate t, 
			String fieldName,
			Object value)
			throws ValidateException {
		
		String defaultMessage = "%s的值不能小于%s";

		if(value == null) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
			
		}
		
		if(value instanceof Integer || value instanceof Long || value instanceof Byte || value instanceof Short) {
			
			Long v = Long.parseLong(value.toString());
			
			Long min = Long.parseLong(t.value());
			
			if(v < min) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
				
			}
			
		} else if(value instanceof Double || value instanceof Float) {
			
			Double v = Double.parseDouble(value.toString());
			
			Double min = Double.parseDouble(t.value());
			
			if(v < min) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
				
			}
			
		} else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "MinValueValidate only support int|long|byte|short|double|float");
			
		}
		return true;
	}
	
}
