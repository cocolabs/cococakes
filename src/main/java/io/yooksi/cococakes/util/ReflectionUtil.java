package io.yooksi.cococakes.util;

import java.lang.reflect.Method;

public class ReflectionUtil {

	public static Method setMethodAccessible(Class<?> clazz, String methodName, Class<?>...params) {

		try {
			Method method = clazz.getDeclaredMethod(methodName, params);
			method.setAccessible(true);
			return method;
		}
		catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		}
	}
}
