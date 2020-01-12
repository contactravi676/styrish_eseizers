package com.styrish.courses.topics.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;

public class TopicUploadVideoAction extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = 1L;
	private File document;
	private String documentFileName;
	private String documentContentType;
	private String courseTopicChoice;
	private String courseId;
	private String documentTitle;
	private String documentDescription;
	private InputStream inputStream;
    private String fileName;
    private long contentLength;
    private String documentPath;
    private String userId;
	
	private ServletContext servletContext;


	public void setServletContext(ServletContext context) {
		servletContext=context;
	}
	public File getDocument() {
		return document;
	}



	public void setDocument(File document) {
		this.document = document;
	}



	public String getDocumentFileName() {
		return documentFileName;
	}



	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}



	public String getDocumentContentType() {
		return documentContentType;
	}



	public void setDocumentContentType(String documentContentType) {
		this.documentContentType = documentContentType;
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
	
	
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String execute() {


		CourseTopicsDaoImpl courseTopicsDaoImpl=new CourseTopicsDaoImpl();
		HttpSession session=ServletActionContext.getRequest().getSession(false);
		if(session!=null) {
			userId=(String) session.getAttribute("userId");
		}
		
	
		String documentAdditionPath=courseTopicsDaoImpl.getDocumentAdditionPath(courseTopicChoice);
		documentFileName=documentAdditionPath+"_"+documentFileName;
		String targetPath = (String) servletContext.getInitParameter("host");
		
		File fileToCreate = new File(targetPath, documentFileName);
		setDocumentFileName(documentFileName);
		try {
			FileUtils.copyFile(this.document, fileToCreate);

			courseTopicsDaoImpl.insertVideo(this);
			documentPath=targetPath+"/"+documentFileName;
			 File fileToDownload = new File(documentPath);
			 fileName = fileToDownload.getName();
			 
			 inputStream=new FileInputStream(fileToDownload);
		} catch (IOException e) {
			addActionError(e.getMessage());
		}
		return "success";
	}

}
