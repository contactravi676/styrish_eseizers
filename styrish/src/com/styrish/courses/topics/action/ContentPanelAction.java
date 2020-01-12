package com.styrish.courses.topics.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;


public class ContentPanelAction extends ActionSupport  implements SessionAware {

	private static final long serialVersionUID = 1L;
	
	private String subjectId;
	private SessionMap<String,Object> sessionMap;
	
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap<String, Object>)map;  
	}  

	
	public String execute()
	{
		
		sessionMap.put("subjectId", subjectId);
		return SUCCESS;
	}
	
	public String createtopic()
	{
		
		sessionMap.put("subjectId", subjectId);
		return SUCCESS;
	}
	
	public String createcontent()
	{
		
		sessionMap.put("subjectId", subjectId);
		return SUCCESS;
	}
	
	public String uploadcontent()
	{
		
		sessionMap.put("subjectId", subjectId);
		return SUCCESS;
	}
	
	
	public String createvideo()
	{
		
		sessionMap.put("subjectId", subjectId);
		return SUCCESS;
	}
	
	public String createexercise()
	{
		
		sessionMap.put("subjectId", subjectId);
		return SUCCESS;
	}
	
	
	

}
