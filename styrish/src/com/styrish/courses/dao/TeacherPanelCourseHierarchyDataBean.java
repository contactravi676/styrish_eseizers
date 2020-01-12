package com.styrish.courses.dao;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

public class TeacherPanelCourseHierarchyDataBean implements ServletResponseAware {
	
	protected HttpServletResponse httpServletResponse;
	 
   @Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;

	}
	
	
	
	

}
