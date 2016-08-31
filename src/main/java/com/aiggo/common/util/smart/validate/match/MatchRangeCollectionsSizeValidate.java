package com.aiggo.common.util.smart.validate.match;

import java.util.Collection;
import java.util.Map;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.RangeCollectionsSizeValidate;

/**
 * 集合大小范围
 *
 */
public class MatchRangeCollectionsSizeValidate extends AbstractMatchValidate<RangeCollectionsSizeValidate>{

	@Override
	public boolean validate(RangeCollectionsSizeValidate t,
			String fieldName,
			Object value)
			throws ValidateException {
		
		String defaultMessage = "%s的大小必须在%s和%s之间";

		if(value == null) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));
			
		}
		
		if(value instanceof Collection) {
			
			Collection<?> array = (Collection<?>) value;
			
			int size = array.size();
			
			if(!(size >= t.min() && size <= t.max())) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}
			
		} 
		else if(value instanceof Map) {
			
			Map<?, ?> map = (Map<?, ?>) value;

			int size = map.size();
			
			if(!(size >= t.min() && size <= t.max())) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}
			
		}
		else if(value.getClass().isArray()) {
					
			Object[] array = (Object[]) value;
			
			int size = array.length;
			
			if(!(size >= t.min() && size <= t.max())) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.min(), t.max()));	

			}

		}
		else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "RangeCollectionSizeValidate only support Collection, Map, Array");
			
		}
		return true;
	}
}
