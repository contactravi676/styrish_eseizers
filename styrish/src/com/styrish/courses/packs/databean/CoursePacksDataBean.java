package com.styrish.courses.packs.databean;

import java.util.List;
import java.util.Map;

import com.styrish.commons.util.objects.CacheManager;
import com.styrish.commons.util.objects.CachedObject;
import com.styrish.courses.packs.dao.CoursePacksDao;

public class CoursePacksDataBean {
	private String courseId;
	private String subcourseId;
	private String courseType;
	private List<Map<String,String>> coursePackList;
	private String mockPackCount;
	private List<Map<String,String>> courseMockPackList;
	
	
	CoursePacksDao coursePacksDao =new CoursePacksDao();

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	
	public String getSubcourseId() {
		return subcourseId;
	}
	public void setSubcourseId(String subcourseId) {
		this.subcourseId = subcourseId;
	}
	
	
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public List<Map<String, String>> getCoursePackList() {
		
		StringBuilder cacheIdentifier = new StringBuilder("coursePackageListDataBeanCacheObject").append("_")
				.append(getCourseId());
		Object identifierObj = cacheIdentifier.toString();

		CachedObject o = (CachedObject) CacheManager.getCache(identifierObj);
		if (o == null) {
			CoursePacksDao coursePacksDao = new CoursePacksDao();
			coursePackList = coursePacksDao.getCoursePack(courseId);
			CachedObject cachedObject = new CachedObject(coursePackList, identifierObj, 180);
			CacheManager.putCache(cachedObject);
		} else {
			coursePackList = (List) o.object;
		}

		return coursePackList;
	}

	public void setCoursePackList(List<Map<String, String>> coursePackList) {
		this.coursePackList = coursePackList;
	}
	public String getMockPackCount() {
		
		mockPackCount=coursePacksDao.getMockPackCount(subcourseId,courseType);
		
		return mockPackCount;
	}
	public void setMockPackCount(String mockPackCount) {
		this.mockPackCount = mockPackCount;
	}
	public List<Map<String, String>> getCourseMockPackList() {
		courseMockPackList=coursePacksDao.getCoursePackBySubcourseId(subcourseId,courseType);
			
		return courseMockPackList;
	}
	public void setCourseMockPackList(List<Map<String, String>> courseMockPackList) {
		this.courseMockPackList = courseMockPackList;
	}
	

}
