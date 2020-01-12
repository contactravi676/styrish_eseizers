package com.styrish.commons.util.objects;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CachedObject implements Cacheable {

	private java.util.Date dateofExpiration = null;
	private Object identifier = null;
	public Object object = null;
	protected static final String CLASSNAME = "CachedObject";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public CachedObject(Object obj, Object id, int minutesToLive) {
		this.object = obj;
		this.identifier = id;
		// minutesToLive of 0 means it lives on indefinitely.
		if (minutesToLive != 0) {
			dateofExpiration = new java.util.Date();
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(dateofExpiration);
			cal.add(Calendar.MINUTE, minutesToLive);
			dateofExpiration = cal.getTime();
		}
	}

	@Override
	public boolean isExpired() {

		final String METHODNAME = "isExpired";
		// Remember if the minutes to live is zero then it lives forever!
		if (dateofExpiration != null) {
			// date of expiration is compared.
			if (dateofExpiration.before(new java.util.Date())) {

				LOGGER.logp(Level.FINEST, CLASSNAME, METHODNAME,
						"CachedResultSet.isExpired:  Expired from Cache! EXPIRE TIME: " + dateofExpiration.toString()
								+ " CURRENT TIME: " + (new java.util.Date()).toString());
				return true;
			} else {
				LOGGER.logp(Level.FINEST, CLASSNAME, METHODNAME,
						"Cache Object Not Expired Yet, will be served !");
				return false;
			}
		} else // This means it lives forever!
			return false;
	     }

	@Override
	public Object getIdentifier() {
       return identifier;
	}

}
