package com.github.sailarize.cache;

import java.util.Map;

import com.github.sailarize.resource.SailResource;

public class CacheRegistry {

	private static Map<Class<?>, Integer> EXPIRATIONS;

	private ThreadLocal<Integer> CURRENT;

	public static void register(Class<? extends SailResource> resourceClass) {

		if (!EXPIRATIONS.containsKey(resourceClass)) {

		}

	}

}
