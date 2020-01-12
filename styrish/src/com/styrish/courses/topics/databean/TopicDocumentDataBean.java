package com.styrish.courses.topics.databean;

import java.util.List;
import java.util.Map;

import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicDocumentDataBean {
	private String topicId;
	private List<Map<String,String>> documentList;
	
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public List<Map<String, String>> getDocumentList() {
		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		documentList = courseTopicsDaoImpl.getTopicDocumentList(topicId);
		return documentList;
	}
	public void setDocumentList(List<Map<String, String>> documentList) {
		this.documentList = documentList;
	}
	
	

}
