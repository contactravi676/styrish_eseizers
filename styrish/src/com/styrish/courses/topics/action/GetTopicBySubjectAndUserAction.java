package com.styrish.courses.topics.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.databean.CoursesTopicDataBean;

public class GetTopicBySubjectAndUserAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1280451410091660003L;

	protected Long subjectId;
	protected Long userId;
	protected List<Map<Long, String>> topicList;
	protected Map<String, Object> sessionMap;

	@Override
	public String execute() throws Exception {

		CoursesTopicDataBean coursesTopicDataBean = new CoursesTopicDataBean();

		if (getUserId() == null) {
			setUserId(Long.valueOf((String) sessionMap.get("userId")));
		}
		coursesTopicDataBean.setSubjectID(getSubjectId());
		coursesTopicDataBean.setUserID(getUserId());

		setTopicList(coursesTopicDataBean.getTopicListByCourseAndUser());

		return "SUCCESS".toLowerCase();
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Map<Long, String>> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<Map<Long, String>> topicList) {
		this.topicList = topicList;
	}

}
