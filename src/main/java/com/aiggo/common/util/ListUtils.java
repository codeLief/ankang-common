package com.aiggo.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.aiggo.common.util.domain.Function;

/**
 * 
 * @author: qd-ankang
 */
public class ListUtils extends org.apache.commons.collections.ListUtils{
	
	/**
	 * 
		 * @Description: 根据属性取差集
	     * @author: qd-ankang
	     * @date: 2016-7-13 下午4:30:12
	     * @param collection
	     * @param remove
	     * @param property
	     * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <E, T> List<E> removeAll(List<E> collection, List<T> remove, final String property) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		if(StringUtils.isBlank(property)){
			return new ArrayList<>(0);
		}
		if(collection.size() == remove.size()){
			
			return new ArrayList<>(0);
		}
		
		List<Object> removePropertys = transform(remove, new Function<T, Object>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object apply(T input) {
				
				try {
					return BeanUtils.getProperty(input, property);
				} catch (IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) {
					
					e.printStackTrace();
				}
				return input;
			}
			
		});
		
        List list = new ArrayList();
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (removePropertys.contains(BeanUtils.getProperty(obj, property)) == false) {
                list.add(obj);
            }
        }
        return list;
    }
	public static <T, E> List<T> transform(List<E> source, Function<E, T> function){
			
			if(source == null || source.size() == 0){
				
				return new ArrayList<T>(0);
			}
			
			List<T> result = new ArrayList<T>(source.size());
			
			for (E e : source) result.add(function.apply(e));
			
			return result;
		}
}
