package com.aiggo.common.util.smart.validate.match;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.RangeValueValidate;

/**
 * 基本数据类型值范围
 * @author lichao
 *
 */
public class MatchRangeValueValidate extends AbstractMatchValidate<RangeValueValidate>{

	@Override
	public boolean validate(RangeValueValidate t,
			String fieldName,
			Object value)
			throws ValidateException {
		
		String defaultMessage = "%s的值必须在%s和%s之间";
		
		if(value == null) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			
		}
		
		if(value instanceof Integer || value instanceof Long || value instanceof Byte || value instanceof Short) {
			
			Long v = Long.parseLong(value.toString());
			
			Long max = Long.parseLong(t.max());
			Long min = Long.parseLong(t.min());
			
			if(!(v >= min && v <= max)) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			}
			
		} else if(value instanceof Double || value instanceof Float) {
			
			Double v = Double.parseDouble(value.toString());
			
			Double max = Double.parseDouble(t.max());
			Double min = Double.parseDouble(t.min());
			
			if(!(v >= min && v <= max)) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			}
			
		} else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "RangeValueValidate only support int|long|byte|short|double|float");
			
		}
		return true;
	}
}
