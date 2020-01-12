package com.styrish.commons.util.objects;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtils {
	
	protected static final String CLASSNAME = "CommonUtils";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);
	
	
	public static void invalidateCache(Object cacheIdentifier) {
		final String METHODNAME = "invalidateCache";
		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.entering(CLASSNAME, METHODNAME);
		}
		
		  CacheManager.removeCache(cacheIdentifier);
		  
		 if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.exiting(CLASSNAME, METHODNAME);
		}
		
	}

}
