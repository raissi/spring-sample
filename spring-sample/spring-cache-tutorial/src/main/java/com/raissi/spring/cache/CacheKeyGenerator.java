package com.raissi.spring.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class CacheKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(final Object target, final Method method,
			final Object... params) {
		StringBuilder key = new StringBuilder(method.getDeclaringClass().getName()).append(method.getName());
		if(params != null){
			for(Object obj: params){
				key.append(obj.toString());
			}
		}
		return key.toString();
	}
}