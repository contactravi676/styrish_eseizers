package com.styrish.courses.topics.teacherpanel.action.contents.upload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;
import com.styrish.courses.topics.databean.CourseTopic;

public class TopicContentApproveAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488317371051783597L;
	private static final String CLASSNAME = "TopicContentApproveAction";
	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	protected Long usersId;
	protected Long topicID;
	protected int version;
	protected String contentType;
	protected String message;
	protected List<String> errorsList;

	protected Map<String, Object> sessionMap;
	protected CourseTopic courseTopic;

	@Override
	public String execute() throws Exception {

		approveTopic(getCourseTopic());
		setMessage("TOPIC_APPROVED");

		return "success";
	}

	@Override
	public void validate() {

		final String METHODNAME = "validate";

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.entering(CLASSNAME, METHODNAME);
		}

		if (StringUtils.isEmpty(String.valueOf(getTopicID()))) {
			if (errorsList == null) {
				errorsList = new ArrayList<String>();
			}
			errorsList.add("ERR_TOPIC_EMPTY");
			setErrorsList(errorsList);
		}

		if (getErrorsList() != null && !getErrorsList().isEmpty()) {
			setActionErrors(getErrorsList());
		} else {
			CourseTopic courseTopic = new CourseTopic();
			courseTopic.setTopicId(getTopicID());
			courseTopic.setVersion(getVersion());
			courseTopic.setStatus(1);
			courseTopic.setTopicType(getContentType());
			courseTopic.setUserId(Long.valueOf((String)sessionMap.get("userId")));
			setCourseTopic(courseTopic);
		}

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.exiting(CLASSNAME, METHODNAME);
		}

	}

	protected void approveTopic(CourseTopic courseTopic) {
           
		CourseTopicsDaoImpl courseTopicsDao = new CourseTopicsDaoImpl();
		courseTopicsDao.updateTopic(getCourseTopic());
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public CourseTopic getCourseTopic() {
		return courseTopic;
	}

	public void setCourseTopic(CourseTopic courseTopic) {
		this.courseTopic = courseTopic;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public List<String> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public Long getTopicID() {
		return topicID;
	}

	public void setTopicID(Long topicID) {
		this.topicID = topicID;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
