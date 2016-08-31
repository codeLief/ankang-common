package com.aiggo.common.util.smart.validate.match;

import java.util.Collection;
import java.util.Map;

import com.aiggo.common.util.constants.HttpStatus;
import com.aiggo.common.util.exception.ValidateException;
import com.aiggo.common.util.smart.validate.match.rule.MinCollectionsSizeValidate;

/**
 * 最小集合长度
 *
 */
public class MatchMinCollectionsSizeValidate extends AbstractMatchValidate<MinCollectionsSizeValidate>{

	@Override
	public boolean validate(MinCollectionsSizeValidate t, 
			String fieldName,
			Object value)
			throws ValidateException {
		
		String defaultMessage = "%s的大小不能小于%s";

		if(value == null) {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
					getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), t.size()));
			
		}
		
		int minSize = t.size();
		
		if(value instanceof Collection) {
			
			Collection<?> collection = (Collection<?>) value;
			
			if(collection.size() < minSize) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minSize));
			}
			
		}
		else if(value instanceof Map) {
			
			Map<?, ?> map = (Map<?, ?>) value;

			if(map.size() < minSize) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minSize));
			}
			
		}
		else if(value.getClass().isArray()) {
					
			Object[] array = (Object[]) value;
			
			if(array.length < minSize) {
				
				throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						getMessage(t.message(), defaultMessage, getName(t.name(), fieldName), minSize));
			}

		}
		else {
			
			throw new ValidateException(HttpStatus.BAD_REQUEST.getStatusCode(), "MinCollectionSizeValidate only support  Collection, Map, Array");
			
		}
		return true;
	}
	
}
