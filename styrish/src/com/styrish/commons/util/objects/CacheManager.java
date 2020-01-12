package com.styrish.commons.util.objects;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheManager {

	private static java.util.HashMap<Object, Object> cacheHashMap = new java.util.HashMap<Object, Object>();
	protected static final String CLASSNAME = "CacheManager";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public CacheManager() {

	}

	public static void putCache(Cacheable object) {
		cacheHashMap.put(object.getIdentifier(), object);
	}

	public static Cacheable getCache(Object identifier) {
		System.out.println(cacheHashMap.containsKey(identifier));

		Cacheable object = null;
		synchronized (cacheHashMap) {

			object = (Cacheable) cacheHashMap.get(identifier);

		}

		if (object == null)
			return null;
		if (object.isExpired()) {
			cacheHashMap.remove(identifier);
			return null;
		} else {
			return object;
		}
	}
	
	public static void removeCache(Object identifier) {
		final String METHODNAME = "removeCache";
		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.entering(CLASSNAME, METHODNAME);
		}
		if (cacheHashMap != null && cacheHashMap.containsKey(identifier.toString())) {
			cacheHashMap.remove(identifier.toString());
			LOGGER.logp(Level.ALL, CLASSNAME, METHODNAME, "Cached Object removed with identifier"+identifier);
		}
		LOGGER.logp(Level.ALL, CLASSNAME, METHODNAME, "Did not find cached object with identifier"+identifier);
		 if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.exiting(CLASSNAME, METHODNAME);
		}
	}
}
