package com.styrish.courses.topics.action;

import com.opensymphony.xwork2.ActionSupport;

public class TopicDeleteExerciseAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String questionId;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	public String execute() throws Exception {
		
        return SUCCESS;
	}

}
