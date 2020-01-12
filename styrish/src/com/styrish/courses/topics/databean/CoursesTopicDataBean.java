package com.styrish.courses.topics.databean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.styrish.commons.util.objects.CacheManager;
import com.styrish.commons.util.objects.CachedObject;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class CoursesTopicDataBean {

	private String subjectId;
	private String userId;
	private Long subjectID;
	private Long userID;
	private List<Map<String, String>> topicList;
	private List<Map<String, String>> tutorTopicList;
	private List<Map<Long, String>> topicsList;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Long subjectID) {
		this.subjectID = subjectID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public List<Map<String, String>> getTopicList() {
		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		HttpSession session = ServletActionContext.getRequest().getSession(false);

		if ((session != null) && (session.getAttribute("subjectId") != null)) {
			subjectId = (String) session.getAttribute("subjectId");
			userId = (String) session.getAttribute("userId");
		}

		topicList = courseTopicsDaoImpl.getCourseTopicList(subjectId, userId);

		return topicList;
	}

	public void setTopicList(List<Map<String, String>> topicList) {
		this.topicList = topicList;
	}

	public List<Map<String, String>> getTutorTopicList() {
		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();

		HttpSession session = ServletActionContext.getRequest().getSession(false);

		if ((session != null) && (session.getAttribute("userId") != null)) {
			userId = (String) session.getAttribute("userId");
		}

		tutorTopicList = courseTopicsDaoImpl.getCourseTopicListByTutor(userId);

		return tutorTopicList;
	}

	public List<Map<Long, String>> getTopicListByCourseAndUser() {

		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		topicsList = courseTopicsDaoImpl.getCourseTopicLists(getSubjectID(), getUserID());
			
		if (topicsList == null) {
			topicsList = new ArrayList<Map<Long, String>>();
		}

		return topicsList;

	}

	public List<Map<Long, String>> getTopicsList() {
		return topicsList;
	}

	public void setTopicsList(List<Map<Long, String>> topicsList) {
		this.topicsList = topicsList;
	}

	public void setTutorTopicList(List<Map<String, String>> tutorTopicList) {
		this.tutorTopicList = tutorTopicList;
	}

}
