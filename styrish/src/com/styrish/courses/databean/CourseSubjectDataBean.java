package com.styrish.courses.databean;

import java.util.List;
import java.util.Map;

import com.styrish.commons.util.objects.CacheManager;
import com.styrish.commons.util.objects.CachedObject;
import com.styrish.courses.dao.CoursesDaoImpl;

public class CourseSubjectDataBean {

	private List<Map<String, String>> courseSubjectList;
	private List<Map<Long, String>> courseSubjectsList;
	String courseId;
	private CoursesDaoImpl coursesDaoImpl = new CoursesDaoImpl();

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public List<Map<String, String>> getCourseSubjectList() {

		courseSubjectList = coursesDaoImpl.getCourseSubjects(courseId);

		return courseSubjectList;
	}

	public void setCourseSubjectList(List<Map<String, String>> courseSubjectList) {
		this.courseSubjectList = courseSubjectList;
	}

	public List<Map<Long, String>> getCourseSubjectsList() {
		
		StringBuilder cacheIdentifier = new StringBuilder("courseSubjectsDataBeanCacheObject").append("_")
				.append(getCourseId());
		Object identifierObj = cacheIdentifier.toString();

		CachedObject o = (CachedObject) CacheManager.getCache(identifierObj);
		
		if (o == null) {
			courseSubjectsList = coursesDaoImpl.getCourseSubject(courseId);
			CachedObject cachedObject = new CachedObject(courseSubjectsList, identifierObj, 180);
			CacheManager.putCache(cachedObject);
		}else {
			courseSubjectsList = (List)o.object;
		}
		
		
		
		
		return courseSubjectsList;
	}

	public void setCourseSubjectsList(List<Map<Long, String>> courseSubjectsList) {
		this.courseSubjectsList = courseSubjectsList;
	}
	
	

}
