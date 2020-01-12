package com.styrish.mockvideo.action;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;
import com.styrish.mockvideo.dao.MockVideoDaoImpl;

public class TutorMockVideoUploadAction extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = 1L;
	private File document;
	private String documentFileName;
	private String documentContentType;
	private String subcourseId;
	private String courseId;
	private String userId;
	private String documentTitle;
	private String documentDescription;
	private String documentPath;
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




	public String getSubcourseId() {
		return subcourseId;
	}




	public void setSubcourseId(String subcourseId) {
		this.subcourseId = subcourseId;
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




	public String getDocumentPath() {
		return documentPath;
	}




	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}




	public String execute() {
		try {



			MockVideoDaoImpl mockVideoDaoImpl=new MockVideoDaoImpl();
			String documentAdditionPath=mockVideoDaoImpl.getMockVideoAdditionPath(subcourseId);
			documentFileName=documentAdditionPath+"_"+documentFileName;

			String targetPath = (String) servletContext.getInitParameter("host");
			File fileToCreate = new File(targetPath, documentFileName);

			setDocumentFileName(documentFileName);

			FileUtils.copyFile(this.document, fileToCreate);
			documentPath=targetPath+"/"+documentFileName;  

			HttpSession session=ServletActionContext.getRequest().getSession(false);
			if(session.getAttribute("courseIdSession")!=null) {
				courseId=(String) session.getAttribute("courseIdSession");
				userId=(String) session.getAttribute("userId");
			}

			mockVideoDaoImpl.createMockVideo(this);
		} catch (IOException e) {
			addActionError(e.getMessage());
		}
		return "success";
	}



}