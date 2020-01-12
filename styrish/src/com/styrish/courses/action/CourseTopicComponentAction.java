package com.styrish.courses.action;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;

public class CourseTopicComponentAction extends ActionSupport {

	private static final long serialVersionUID = -2613425890762568273L;

	private Map<String,String> courseTopicComponentMap;
	private String topicId;
	private String content;

	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}


	public Map<String, String> getCourseTopicComponentMap() {
		return courseTopicComponentMap;
	}
	public void setCourseTopicComponentMap(Map<String, String> courseTopicComponentMap) {
		this.courseTopicComponentMap = courseTopicComponentMap;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	//ConvertToHTML convertToHTML=new ConvertToHTML();
	public String execute() throws Exception {


		return "success";
	}


}
