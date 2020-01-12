package com.styrish.courses.databean;

import java.util.List;
import java.util.Map;

import com.styrish.commons.util.objects.CacheManager;
import com.styrish.commons.util.objects.CachedObject;
import com.styrish.courses.dao.CoursesDaoImpl;

public class CoursesDataBean {

	private List<Map<String, String>> courseList;
	private CoursesDaoImpl coursesDaoImpl = new CoursesDaoImpl();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, String>> getCourseList() {
		CachedObject o = (CachedObject) CacheManager.getCache("courseListCacheObject");
		if (o == null) {
			courseList = coursesDaoImpl.getCourses();
			CachedObject cachedObject = new CachedObject(courseList, "courseListCacheObject", 180);
			CacheManager.putCache(cachedObject);
		} else {
			courseList = (List) o.object;
		}

		return courseList;
	}

	public void setCourseList(List<Map<String, String>> courseList) {
		this.courseList = courseList;
	}

}
