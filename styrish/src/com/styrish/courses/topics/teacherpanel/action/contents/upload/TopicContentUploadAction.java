package com.styrish.courses.topics.teacherpanel.action.contents.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.courses.topics.dao.CourseTopicsDaoImpl;
import com.styrish.courses.topics.databean.CourseTopic;

public class TopicContentUploadAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6226376767317016623L;
	private static final String CLASSNAME = "TopicContentUploadAction";
	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	protected String filesPath;
	protected int version;
	protected String fileName;
	protected File fileUpload;
	protected String directoryPath;
	protected String destinationPath;
	protected Long courseID;
	protected String courseName;
	protected Long subjectID;
	protected String subjectName;
	protected Long topicID;
	protected String topicName;
	protected String contentType;
	protected String topicNameText;
	protected boolean topicCreatedOnServer;
	protected boolean topicCreatedInDb;
	protected String message;
	protected String errorMessage;
	protected Map<String, Object> sessionMap;
	protected List<String> errorsList;
	protected CourseTopic courseTopic;
	protected CourseTopicsDaoImpl courseTopicDAO;

	@Override
	public String execute() throws IOException {

		CourseTopic courseTopic = getCourseTopic();
		if (courseTopic.getTopicId() != null) {
			fetchExistingTopic(courseTopic);
		}
		Long courseTopicId = courseTopic.getTopicId();
		int version = courseTopic.getVersion().intValue();
		int status = courseTopic.getStatus().intValue();
		constructDestinationPath();

		String file = renameFile(getFileName(), (String) getSessionMap().get("userId"));

		setFileName(file.toString());

		if (courseTopicId != null) {
			version = version + 1;
			setFileName(renameFile(getFileName(), new StringBuilder("v").append(version).toString()));
			courseTopic.setVersion(version);
			courseTopic.setTopicType(getContentType());
			courseTopic.setTopicPath(new StringBuilder(getDestinationPath()).append(getFileName()).toString());
			createNewTopicOnServer(courseTopic);
			if (isTopicCreatedOnServer()) {
				upgradeTopicVersion(courseTopic);
				setTopicCreatedInDb(true);
				setMessage("VERSON_UPDATED");
				setVersion(version);
			} else {
				setErrorMessage("ERR_VERSION_UPGRADING");
			}

		} else {
			courseTopic.setTopicId(System.currentTimeMillis());
			courseTopic.setCourseId(getCourseID());
			courseTopic.setSubjectId(getSubjectID());
			courseTopic.setTopicName(getTopicName());
			courseTopic.setTopicDescription(getTopicName());
			Long userId = Long.valueOf((String) getSessionMap().get("userId"));
			courseTopic.setUserId(userId);
			courseTopic.setStatus(0);
			courseTopic.setVersion(1);
			courseTopic.setTopicType(getContentType());
			courseTopic.setTopicPath(new StringBuilder(getDestinationPath()).append(getFileName()).toString());
			createNewTopicOnServer(courseTopic);
			if (isTopicCreatedOnServer()) {
				createTopicInDB(courseTopic);
				setTopicCreatedInDb(true);
				setMessage("TOPIC_CREATED");
				setVersion(version);
				setTopicID(courseTopic.getTopicId());
			} else {
				setErrorMessage("ERR_CREATING_TOPIC");
			}

		}

		return "success";

	}

	@Override
	public void validate() {

		final String METHODNAME = "validate";

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.entering(CLASSNAME, METHODNAME);
		}

		if (!StringUtils.isEmpty(courseName) && StringUtils.contains(courseName, "_")) {
			StringTokenizer stringTokenizer = new StringTokenizer(courseName, "_");
			while (stringTokenizer.hasMoreTokens()) {
				String token = (String) stringTokenizer.nextToken();
				Long courseID = Long.valueOf(token);
				String courseName = stringTokenizer.nextToken();
				setCourseID(courseID);
				setCourseName(courseName);
			}

		} else {
			if (errorsList == null) {
				errorsList = new ArrayList<String>();
			}
			errorsList.add("ERR_BLANK_COURSE");
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Course is not present in request");

		}
		if (!StringUtils.isEmpty(subjectName) && StringUtils.contains(subjectName, "_")) {
			StringTokenizer stringTokenizer = new StringTokenizer(subjectName, "_");
			while (stringTokenizer.hasMoreTokens()) {
				String token = (String) stringTokenizer.nextToken();
				Long subjectID = Long.valueOf(token);
				String subjectName = stringTokenizer.nextToken();
				setSubjectID(subjectID);
				setSubjectName(subjectName);
			}
		} else {
			if (errorsList == null) {
				errorsList = new ArrayList<String>();
			}
			errorsList.add("ERR_BLANK_SUBJECT");
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Subject is not present in request");

		}
		if (!StringUtils.isEmpty(topicName) && StringUtils.contains(topicName, "_")) {
			StringTokenizer stringTokenizer = new StringTokenizer(topicName, "_");
			while (stringTokenizer.hasMoreTokens()) {
				String token = (String) stringTokenizer.nextToken();
				Long topicID = Long.valueOf(token);
				String topicName = stringTokenizer.nextToken();
				setTopicID(topicID);
				setTopicName(topicName);
			}
		} else if (!StringUtils.isEmpty(topicNameText)) {

			topicNameText = topicNameText.trim();
			setTopicName(topicNameText);

		} else {
			if (errorsList == null) {
				errorsList = new ArrayList<String>();
			}
			errorsList.add("ERR_BLANK_TOPIC");
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "Topic is not present in request");
		}
		if (StringUtils.isEmpty(contentType)) {
			if (errorsList == null) {
				errorsList = new ArrayList<String>();
			}
			errorsList.add("ERR_BLANK_CONTENT_TYPE");
		}
		if (StringUtils.isEmpty(fileName)) {
			if (errorsList == null) {
				errorsList = new ArrayList<String>();
			}
			errorsList.add("ERR_BLANK_FILE");
		}

		setActionErrors(errorsList);

		if (errorsList == null || errorsList.isEmpty()) {
			CourseTopic courseTopic = new CourseTopic();
			courseTopic.setTopicId(getTopicID());
			courseTopic.setTopicName(getTopicName());
			courseTopic.setTopicType(getContentType());

			setCourseTopic(courseTopic);
		}

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.exiting(CLASSNAME, METHODNAME);
		}

	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;

	}

	protected void createTopicInDB(CourseTopic courseTopic) {
		CourseTopicsDaoImpl courseTopicsDao = getCourseTopicDAO();
		courseTopicsDao.createNewTopic(courseTopic);

	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	protected void createNewTopicOnServer(CourseTopic courseTopic) {

		try {

			File fileToCreate = new File(courseTopic.getTopicPath());

			FileUtils.copyFile(getFileUpload(), fileToCreate);
			setTopicCreatedOnServer(fileToCreate.exists());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	protected void upgradeTopicVersion(CourseTopic courseTopic) {
		CourseTopicsDaoImpl courseTopicsDao = getCourseTopicDAO();
		courseTopicsDao.updateTopicVersion(courseTopic);

	}

	protected void fetchExistingTopic(CourseTopic courseTopic) {

		CourseTopicsDaoImpl courseTopicsDao = getCourseTopicDAO();
		courseTopic = courseTopicsDao.fetchTopicById(courseTopic);

	}

	protected void constructDestinationPath() {
		final String METHODNAME = "constructDestinationPath";

		StringBuilder strB = new StringBuilder(getFilesPath());
		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.entering(CLASSNAME, METHODNAME);
		}
		String dirPath = new StringBuilder().append(getCourseName()).append("//").append(getSubjectName()).append("//")
				.append(getTopicName()).append("//").append(getContentType()).append("//").toString();
		setDirectoryPath(dirPath);

		strB.append(getCourseName()).append("//").append(getSubjectName()).append("//").append(getTopicName())
				.append("//").append(getContentType()).append("//");
		setDestinationPath(strB.toString());
		LOGGER.logp(Level.ALL, CLASSNAME, METHODNAME, "File Path Created Is " + getDestinationPath());

	}

	public CourseTopicsDaoImpl getCourseTopicDAO() {

		if (courseTopicDAO == null) {
			courseTopicDAO = new CourseTopicsDaoImpl();
		}

		return courseTopicDAO;
	}

	protected String renameFile(String file, String prefix) {

		if (fileName != null) {
			String fileName = file.substring(0, file.indexOf("."));
			String fileType = file.substring(file.indexOf("."), file.length());

			StringBuilder strB = new StringBuilder(fileName);
			strB.append("_").append(prefix).append(fileType);
			file = strB.toString();

		}
		return file;

	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public void setCourseTopicDAO(CourseTopicsDaoImpl courseTopicDAO) {
		this.courseTopicDAO = courseTopicDAO;
	}

	public String getDestinationPath() {
		return destinationPath;
	}

	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	public Long getCourseID() {
		return courseID;
	}

	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Long subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Long getTopicID() {
		return topicID;
	}

	public void setTopicID(Long topicID) {
		this.topicID = topicID;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTopicNameText() {
		return topicNameText;
	}

	public void setTopicNameText(String topicNameText) {
		this.topicNameText = topicNameText;
	}

	public List<String> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public CourseTopic getCourseTopic() {
		return courseTopic;
	}

	public void setCourseTopic(CourseTopic courseTopic) {
		this.courseTopic = courseTopic;
	}

	public boolean isTopicCreatedOnServer() {
		return topicCreatedOnServer;
	}

	public void setTopicCreatedOnServer(boolean topicCreatedOnServer) {
		this.topicCreatedOnServer = topicCreatedOnServer;
	}

	public boolean isTopicCreatedInDb() {
		return topicCreatedInDb;
	}

	public void setTopicCreatedInDb(boolean topicCreatedInDb) {
		this.topicCreatedInDb = topicCreatedInDb;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
