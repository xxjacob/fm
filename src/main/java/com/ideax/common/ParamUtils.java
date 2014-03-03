package com.ideax.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;

import com.ideax.common.exception.IllegalParamException;

/**
 * 获取参数的工具类
 * 
 * @author <a href="xuxin03@baidu.com">xuxin03</a>
 */
public class ParamUtils {
	/**
	 * 将string参数转换成指定类型
	 * 
	 * @param str
	 * @param defaultVal
	 *            返回值类型 ,非空,str为空时原始类型会返回该值，以避免cast错误
	 * @param nullCode
	 *            为空时抛出的异常中的错误码,不大于0时说明可以为空
	 * @param castErr
	 *            str转换成int byte 等，抛出的异常
	 * @return
	 * @throws IllegalParamException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String str, T defaultVal, int nullCode,
			int castErr) {
		if (StringUtils.isEmpty(str)) {
			if (nullCode > 0)
				throw new IllegalParamException(nullCode);
			if (defaultVal != null
					&& ClassUtils.isPrimitiveWrapper(defaultVal.getClass())) {
				return defaultVal;
			}
			return null;
		}
		Class<?> clz = defaultVal.getClass();
		Object result = null;
		try {
			if (String.class.isAssignableFrom(clz)) {
				result = str;
			} else if (clz.equals(Integer.class)) {
				result = (Integer) Integer.parseInt(str);
			} else if (clz.equals(Byte.class)) {
				result = (Byte) Byte.parseByte(str);
			} else if (clz.equals(Long.class)) {
				result = (Long) Long.parseLong(str);
			}
		} catch (NumberFormatException e) {
			if (castErr > 0)
				throw new IllegalParamException(castErr);
		}
		return (T) result;
	}

	/**
	 * 将string[] 参数转换成指定类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(String[] str, Class<T> clz, int nullCode,
			int castErr) {
		if (str == null || str.length == 0) {
			if (nullCode > 0)
				throw new IllegalParamException(nullCode);
			return null;
		}
		Object result = null;
		try {
			if (String[].class.equals(clz)) {
				result = str;
			} else if (clz.equals(int[].class)) {
				int[] is = new int[str.length];
				for (int i = 0; i < str.length; i++) {
					String s = str[i];
					is[i] = Integer.parseInt(s);
				}
				result = is;
			}
		} catch (NumberFormatException e) {
			throw new IllegalParamException(castErr);
		}
		return (T) result;
	}

	public static Date getDate(String str, String format, int nullCode,
			int castErr) {
		if (StringUtils.isEmpty(str)) {
			if (nullCode > 0)
				throw new IllegalParamException(nullCode);
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str);
		} catch (ParseException e) {
			throw new IllegalParamException(castErr);
		}
	}
}
