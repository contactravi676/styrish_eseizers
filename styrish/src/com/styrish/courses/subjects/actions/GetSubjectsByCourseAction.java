package com.styrish.courses.subjects.actions;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.databean.CourseSubjectDataBean;

public class GetSubjectsByCourseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5531712717328244660L;
	protected List<Map<Long, String>> subjectList;
	protected String courseId;

	@Override
	public String execute() throws Exception {

		if (!StringUtils.isEmpty(courseId)) {
			
			CourseSubjectDataBean coursesSubjectDataBean = new CourseSubjectDataBean();
			coursesSubjectDataBean.setCourseId(getCourseId());
			subjectList = coursesSubjectDataBean.getCourseSubjectsList();
			setSubjectList(subjectList);

		}

		return "success";
	}

	public List<Map<Long, String>> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Map<Long, String>> subjectList) {
		this.subjectList = subjectList;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}
