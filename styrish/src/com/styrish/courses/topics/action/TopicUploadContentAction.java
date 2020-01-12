package com.styrish.courses.topics.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicUploadContentAction extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = 1L;
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private String courseTopicChoice;
	private String courseId;
	private String userId;
	private ServletContext servletContext;
	private String documentTitle;
	private String documentDescription;
	private String documentPath;
    private InputStream inputStream;
    private String fileName;
	private long contentLength;


	public void setServletContext(ServletContext context) {
		servletContext=context;
	}
	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}




	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public String getCourseTopicChoice() {
		return courseTopicChoice;
	}
	public void setCourseTopicChoice(String courseTopicChoice) {
		this.courseTopicChoice = courseTopicChoice;
	}


	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}



	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public String getDocumentDescription() {
		return documentDescription;
	}
	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;

	}
	
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public String execute() {
		try {


			CourseTopicsDaoImpl courseTopicsDaoImpl=new CourseTopicsDaoImpl();
			String filePath = (String) servletContext.getInitParameter("host");

			String documentAdditionPath=courseTopicsDaoImpl.getDocumentAdditionPath(courseTopicChoice);
			userImageFileName=documentAdditionPath+"_"+userImageFileName;
		
			File fileToCreate = new File(filePath, userImageFileName);

			FileUtils.copyFile(this.userImage, fileToCreate);

			setUserImageFileName(userImageFileName);

			documentPath=filePath+"/"+userImageFileName;	
			HttpSession session=ServletActionContext.getRequest().getSession(false);
			if(session.getAttribute("courseIdSession")!=null) {
				courseId=(String) session.getAttribute("courseIdSession");
				userId=(String) session.getAttribute("userId");
			}

      int i=courseTopicsDaoImpl.insertDocuments(this);
      
      File fileToDownload = new File(documentPath);
		 fileName = fileToDownload.getName();
		 
		 inputStream=new FileInputStream(fileToDownload);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}



}