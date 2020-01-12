package com.styrish.tutor.action;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;


public class TutorPanelAction extends ActionSupport implements SessionAware {
	
private static final long serialVersionUID = 1L;

	private String courseId;
	private SessionMap<String,Object> sessionMap;
	
	public String getCourseId() {
		return courseId;
	}

   public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

   @Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap<String, Object>)map;  
	}  




	public String execute() throws Exception {
		
		sessionMap.put("courseIdSession", courseId);
	
		return "success";
	}

	 



}
