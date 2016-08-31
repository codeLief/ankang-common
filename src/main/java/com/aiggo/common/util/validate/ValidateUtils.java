package com.aiggo.common.util.validate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.aiggo.common.util.GsonUtil;
import com.aiggo.common.util.exception.ValidateException;

/**
 * @Description: 校验工具
 * @author: ankang
 * @date: 2015年4月23日 上午11:53:12
 */
public class ValidateUtils {

	private static final String ALL_NOT_NULL = "%s至少要有一个参数不为空";

	private static final String NOT_NULL = "%s不能为空！";

	private static final String EXISTS_TYPE = "%s不在要求类型内！";

	/**
	 * 
	 * @Description: 校验单个参数里面至少有一个不为空
	 * @author: ankang
	 * @date: 2015-5-27 下午8:50:09
	 * @param fields
	 *            参数:参数值
	 * @param number
	 *            需要几个参数不为空
	 */
	public static void oneNotNull(int number, String... fields) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] split = null;
		for (String field : fields) {
			split = field.split(":");
			map.put(split[0], split.length < 2 ? "" : split[1]);
		}
		oneNotNull(map, number,
				(String[]) map.keySet().toArray(new String[fields.length]));
	}

	/**
	 * 
	 * @Description: 校验的参数里面至少有一个不为空
	 * @author: ankang
	 * @date: 2015-5-27 下午8:49:22
	 * @param paramMap
	 * @param fields
	 */
	public static void oneNotNull(Map<String, Object> paramMap, int number,
			String... fields) {
		int count = 0;
		StringBuilder rtnValueSb = new StringBuilder(100);
		for (String field : fields) {
			Object value = paramMap.get(field);
			if (isNull(value)) {
				count++;
				rtnValueSb.append(field).append(",");
			}
		}
		if (fields.length - count < number) {
			throwsAShException(String.format(ALL_NOT_NULL,
					"[" + rtnValueSb.substring(0, rtnValueSb.length() - 1)
							+ "]"));
		}

	}

	/**
	 * 
	 * @Description: 校验的对象里面指定参数里面至少有一个不为空
	 * @author: ankang
	 * @date: 2015-5-27 下午8:12:50
	 * @param bean
	 * @param fields
	 */
	public static void oneNotNull(Object bean, int number, String... fields) {

		if (fields == null || fields.length == 0) {
			return;
		}
		if (bean == null) {
			// 抛出校验异常
			throwsAShException(String.format(NOT_NULL, Arrays.toString(fields)));
		}
		Class<?> cls = bean.getClass();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		putFieldToMap(fieldMap, cls, bean);
		oneNotNull(fieldMap, number, fields);
	}

	/**
	 * @Description: 校验字段不能为空
	 * @author: ankang
	 * @date: 2015年4月23日 下午2:01:01
	 * @param paramMap
	 *            需校验map
	 * @param fields
	 *            需校验字段
	 */
	public static void notNull(Map<String, Object> paramMap, String... fields) {

		if (fields == null || fields.length == 0) {
			return;
		}

		if (paramMap == null || paramMap.isEmpty()) {
			// 抛出校验异常
			throwsAShException(String.format(NOT_NULL, Arrays.toString(fields)));
		}

		// 添加不符合要求的字段
		StringBuilder rtnValueSb = new StringBuilder(100);
		for (String field : fields) {
			Object value = paramMap.get(field);
			if (isNull(value)) {
				rtnValueSb.append(field).append(",");
			}
		}
		if (rtnValueSb.length() > 0) {
			// 抛出校验异常
			throwsAShException(String.format(NOT_NULL,
					"[" + rtnValueSb.substring(0, rtnValueSb.length() - 1)
							+ "]"));
		}
	}

	/**
	 * @Description: 校验字段不能为空
	 * @author: ankang
	 * @date: 2015年4月23日 下午8:57:02
	 * @param bean
	 *            需校验bean
	 * @param fields
	 *            需校验字段
	 */
	public static void notNull(Object bean, String... fields) {
		if (fields == null || fields.length == 0) {
			return;
		}

		if (bean == null) {
			// 抛出校验异常
			throwsAShException(String.format(NOT_NULL, Arrays.toString(fields)));
		}

		// 获取bean中的字段
		Class<?> cls = bean.getClass();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		putFieldToMap(fieldMap, cls, bean);

		// 判断是否为空
		notNull(fieldMap, fields);
	}

	/**
	 * @Description: 将类中属性赋值到map中
	 * @author: ankang
	 * @date: 2015年5月7日 下午4:55:06
	 * @param paramMap
	 *            结果
	 * @param cls
	 *            bean的类
	 * @param bean
	 *            需要获取的bean
	 */
	public static void putFieldToMap(Map<String, Object> paramMap,
			Class<?> cls, Object bean) {

		paramMap = GsonUtil.objToMap(bean);
	}

	/**
	 * @Description: 判断是否为空
	 * @author: ankang
	 * @date: 2015年4月23日 下午7:36:28
	 * @param value
	 *            参数
	 * @return true:空，false：非空
	 */
	public static boolean isNull(Object value) {
		if (value == null) {
			return true;
		}
		String temp = String.valueOf(value);

		return StringUtils.isBlank(temp);
	}

	/**
	 * @Description: 校验输入类型是否在为规定的值 ,可以为空
	 * @author: ankang
	 * @date: 2015年4月23日 下午4:52:26
	 * @param paramMap
	 *            需校验map
	 * @param fields格式为
	 *            field:range： field 需校验字段,field在map中的实际类型只能是基本类型和字符串 range
	 *            符合规定值 针对fields格式输入错误直接抛出
	 */
	public static void existsType(Map<String, Object> paramMap,
			String... fields) {
		if (paramMap == null || paramMap.isEmpty() || fields == null
				|| fields.length == 0) {
			return;
		}

		StringBuilder rtnValueSb = new StringBuilder(100);

		String[] field = null;
		for (String tmp : fields) {
			field = tmp.split(":");

			// 为空则不进行校验
			if (paramMap.get(field[0]) == null) {
				continue;
			}
			if (field[1].indexOf(paramMap.get(field[0]).toString()) == -1) {
				// 抛出校验异常
				rtnValueSb.append(field[0]).append(",");
			}
		}
		if (rtnValueSb.length() > 0) {
			// 抛出校验异常
			throwsAShException(String.format(EXISTS_TYPE,
					"[" + rtnValueSb.substring(0, rtnValueSb.length() - 1)
							+ "]"));
		}
	}

	/**
	 * @Description: 校验输入类型是否在为规定的值 ,可以为空
	 * @author: ankang
	 * @date: 2015年4月24日 上午9:39:19
	 * @param bean
	 *            需校验bean
	 * @param fields格式为
	 *            field:range： field 需校验字段,field在map中的实际类型只能是基本类型和字符串 range
	 *            符合规定值 针对fields格式输入错误直接抛出
	 */
	public static void existsType(Object bean, String... fields) {
		if (bean == null || fields == null || fields.length == 0) {
			return;
		}

		Class<?> cls = bean.getClass();
		Field[] clsFields = cls.getDeclaredFields();
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		for (Field clsField : clsFields) {
			clsField.setAccessible(true);
			Object object = null;
			try {
				object = clsField.get(bean);
			} catch (Exception e) {
				ValidateException.CHECK_FAIL.newInstance(e, clsField.getName()
						+ "校验异常");
			}
			fieldMap.put(clsField.getName(), object);
		}
		// 校验
		existsType(fieldMap, fields);
	}

	public static void throwsAShException(String msg) {
		throw ValidateException.CHECK_FAIL.newInstance(msg);
	}

}
