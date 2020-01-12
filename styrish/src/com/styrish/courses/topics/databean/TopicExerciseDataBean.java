package com.styrish.courses.topics.databean;

import java.util.List;
import java.util.Map;

import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicExerciseDataBean {

	private String topicId;
	private String questionId;
	private Map<String,String> questionMap;
	private List<Map<String,String>> exerciseList;
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public List<Map<String, String>> getExerciseList() {

		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		exerciseList=courseTopicsDaoImpl.getTopicExerciseList(topicId);

		return exerciseList;
	}
	public void setExerciseList(List<Map<String, String>> exerciseList) {
		this.exerciseList = exerciseList;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public Map<String, String> getQuestionMap() {
		CourseTopicsDaoImpl courseTopicsDaoImpl = new CourseTopicsDaoImpl();
		questionMap=courseTopicsDaoImpl.getExerciseQuestion(questionId);

		return questionMap;
	}
	public void setQuestionMap(Map<String, String> questionMap) {
		this.questionMap = questionMap;
	}






}
