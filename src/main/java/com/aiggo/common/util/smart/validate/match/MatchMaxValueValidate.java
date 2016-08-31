package com.aiggo.common.util.smart.validate.match;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.MaxValueValidate;

/**
 * 基本数据类型最大值
 * Null满足条件
 *
 */
public class MatchMaxValueValidate extends AbstractMatchValidate<MaxValueValidate>{

	@Override
	public boolean validate(MaxValueValidate t, 
			String fieldName,
			Object value)
			throws ValidateException {
		
		if(value == null) {
			
			return true;
		}
		
		String defaultMessage = "%s的值不能大于%s";

		if(value instanceof Integer || value instanceof Long || value instanceof Byte || value instanceof Short) {
			
			Long v = Long.parseLong(value.toString());
			
			Long max = Long.parseLong(t.value());
			
			if(v > max) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
			}
			
		} else if(value instanceof Double || value instanceof Float) {
			
			Double v = Double.parseDouble(value.toString());
			
			Double max = Double.parseDouble(t.value());
			
			if(v > max) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.value()));
				
			}
			
		} else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "MaxValueValidate only support int|long|byte|short|double|float");
			
		}
		return true;
	}
}
