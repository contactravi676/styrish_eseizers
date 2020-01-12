package com.styrish.courses.topics.databean;

import java.io.Serializable;

public class CourseTopic implements Serializable {

	/**
	 * Course Topic DTO having its getters and setters
	 */
	private static final long serialVersionUID = 1014927254502544519L;

	protected Long topicId;
	protected Long courseId;
	protected Long subjectId;
	protected Long userId;
	protected String topicName;
	protected String topicDescription;
	protected int status;
	protected String topicType;
	protected String topicPath;
	protected int version;

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public String getTopicPath() {
		return topicPath;
	}

	public void setTopicPath(String topicPath) {
		this.topicPath = topicPath;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CourseTopic [topicId=");
		builder.append(topicId);
		builder.append(", courseId=");
		builder.append(courseId);
		builder.append(", subjectId=");
		builder.append(subjectId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", topicName=");
		builder.append(topicName);
		builder.append(", topicDescription=");
		builder.append(topicDescription);
		builder.append(", status=");
		builder.append(status);
		builder.append(", topicType=");
		builder.append(topicType);
		builder.append(", topicPath=");
		builder.append(topicPath);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}

}
