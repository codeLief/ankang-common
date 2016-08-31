package com.aiggo.common.util.smart.validate.match;

import java.util.Collection;
import java.util.Map;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.MaxCollectionsSizeValidate;

/**
 * 集合最大大小
 * Null满足条件
 */
public class MatchMaxCollectionsSizeValidate extends AbstractMatchValidate<MaxCollectionsSizeValidate>{

	@Override
	public boolean validate(MaxCollectionsSizeValidate t, 
			String fieldName,
			Object value)
			throws ValidateException {
		
		if(value == null) {
			
			return true;
		}
		
		String defaultMessage = "%s的大小不能大于%s";

		int maxSize = t.size();
		
		if(value instanceof Collection) {
			
			Collection<?> collection = (Collection<?>) value;
			
			if(collection.size() > maxSize) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), maxSize));
			}
			
		}
		else if(value instanceof Map) {
			
			Map<?, ?> collection = (Map<?, ?>) value;
			
			if(collection.size() > maxSize) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), maxSize));
			}
			
		}
		else if(value.getClass().isArray()) {
			
			Object[] array = (Object[]) value;
			
			if(array.length > maxSize) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), maxSize));
			}
			
		}
		else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "MaxCollectionSizeValidate only support  Collection, Map, Array");
			
		}
		return true;
	}
	
}
