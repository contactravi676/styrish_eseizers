package com.styrish.courses.topics.databean;

import java.util.List;
import java.util.Map;

import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicVideoDataBean {

	private String topicId;
	private List<Map<String,String>> videoList;

	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public List<Map<String, String>> getVideoList() {
		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		videoList=courseTopicsDaoImpl.getTopicVideoList(topicId);

		return videoList;
	}
	public void setVideoList(List<Map<String, String>> videoList) {
		this.videoList = videoList;
	}



}
