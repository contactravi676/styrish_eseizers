package com.styrish.courses.topics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.styrish.connections.JDBCHelper;
import com.styrish.courses.topics.action.TopicCreateAction;
import com.styrish.courses.topics.action.TopicCreateContentAction;
import com.styrish.courses.topics.action.TopicCreateExerciseAction;
import com.styrish.courses.topics.action.TopicDeleteExerciseAction;
import com.styrish.courses.topics.action.TopicUpdateAction;
import com.styrish.courses.topics.action.TopicUpdateExerciseAction;
import com.styrish.courses.topics.action.TopicUploadContentAction;
import com.styrish.courses.topics.action.TopicUploadVideoAction;
import com.styrish.courses.topics.databean.CourseTopic;

public class CourseTopicsDaoImpl extends JDBCHelper {
	protected static final String CLASSNAME = "CourseTopicsDaoImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	protected static final String FETCH_TOPIC_BY_ID_SQL = "select coursetopics.coursetopic_id,coursetopics.topic_name,coursetopics.shortdescription,coursetopics.status,coursetopictype.type,coursetopictype.path,coursetopictype.version"
			+ " from coursetopics left outer join coursetopictype on (coursetopics.coursetopic_id = coursetopictype.coursetopic_id) where coursetopics.coursetopic_id=? and type=?";
	
	protected static final String FETCH_TOPIC_BY_NAME_SQL = "select coursetopics.coursetopic_id,coursetopics.topic_name,coursetopics.shortdescription,coursetopics.status,coursetopictype.type,coursetopictype.path,coursetopictype.version"
			+ " from coursetopics left outer join coursetopictype on (coursetopics.coursetopic_id = coursetopictype.coursetopic_id) where coursetopics.topic_name=?";
	
	protected static final String UPDATE_TOPIC_STATUS = "update coursetopics set status = ? where coursetopic_id = ? and user_id=?";
	
	protected static final String UPDATE_TOPIC_TYPE_STATUS = "update coursetopictype set status = ? where coursetopic_id = ? and version = ?";

	public int createTopics(TopicCreateAction topicCreateAction) {
		final String METHODNAME = "createTopics";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;
		try {

			ps = con.prepareStatement(
					"insert IGNORE into coursetopics(topic_name,shortdescription,subject_id,course_id,user_id)values(?,?,?,?,?)");
			ps.setString(1, topicCreateAction.getTopicName());
			ps.setString(2, topicCreateAction.getTopicDescription());
			ps.setString(3, topicCreateAction.getSubjectId());
			ps.setString(4, topicCreateAction.getCourseId());
			ps.setString(5, topicCreateAction.getUserId());

			status = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	public int updateTopics(TopicUpdateAction topicUpdateAction) {
		final String METHODNAME = "updateTopics";

		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;
		try {
			ps = con.prepareStatement(
					"update coursetopics set topic_name =?,shortdescription=? where coursetopic_id=? ");
			ps.setString(1, topicUpdateAction.getTopicName());
			ps.setString(2, topicUpdateAction.getTopicDescription());
			ps.setString(3, topicUpdateAction.getTopicId());

			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	public int deleteTopics(String topicId) {
		final String METHODNAME = "deleteTopics";
		LOGGER.log(Level.INFO, "logging: topicId is {0}  ", new Object[] { topicId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;
		try {
			ps = con.prepareStatement("delete from  coursetopics where coursetopic_id=? ");
			ps.setString(1, topicId);

			status = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	public List<Map<String, String>> getCourseTopicList(String subjectId) {
		final String METHODNAME = "getCourseTopicList";
		LOGGER.log(Level.INFO, "logging: subjectId is {0}  ", new Object[] { subjectId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Map<String, String>> topicList = new ArrayList<Map<String, String>>();

		try {
			ps = con.prepareStatement(
					"SELECT coursetopic_id,topic_name,shortdescription FROM coursetopics WHERE subject_id=?");
			ps.setString(1, subjectId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> topicMap = new HashMap<String, String>();
				topicMap.put("coursetopic_id", rs.getString("coursetopic_id"));
				topicMap.put("topic_name", rs.getString("topic_name"));
				topicMap.put("shortdescription", rs.getString("shortdescription"));

				topicList.add(topicMap);
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return topicList;
	}

	public List<Map<Long, String>> getCourseTopicLists(Long subjectId, Long userId) {
		final String METHODNAME = "getCourseTopicList";
		LOGGER.log(Level.INFO, "logging: subjectId is {0} and userId {1} ", new Object[] { subjectId, userId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<Long, String>> topicList = new ArrayList<Map<Long, String>>();

		try {
			ps = con.prepareStatement(
					"SELECT coursetopic_id,topic_name,shortdescription FROM coursetopics WHERE subject_id=? and user_id=?");
			ps.setLong(1, subjectId);
			ps.setLong(2, userId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<Long, String> topicMap = new HashMap<Long, String>();
				topicMap.put(rs.getLong("coursetopic_id"), rs.getString("topic_name"));
				topicList.add(topicMap);

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return topicList;
	}

	public List<Map<String, String>> getCourseTopicList(String subjectId, String userId) {
		final String METHODNAME = "getCourseTopicList";
		LOGGER.log(Level.INFO, "logging: subjectId is {0} and userId {1} ", new Object[] { subjectId, userId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> topicList = new ArrayList<Map<String, String>>();

		try {
			ps = con.prepareStatement(
					"SELECT coursetopic_id,topic_name,shortdescription FROM coursetopics WHERE subject_id=? and user_id=?");
			ps.setString(1, subjectId);
			ps.setString(2, userId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> topicMap = new HashMap<String, String>();
				topicMap.put("coursetopic_id", rs.getString("coursetopic_id"));
				topicMap.put("topic_name", rs.getString("topic_name"));
				topicMap.put("shortdescription", rs.getString("shortdescription"));

				topicList.add(topicMap);

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return topicList;
	}

	public List<Map<String, String>> getCourseTopicListByTutor(String userId) {
		final String METHODNAME = "getCourseTopicListByTutor";
		LOGGER.log(Level.INFO, "logging:  userId : {0} ", new Object[] { userId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> topicList = new ArrayList<Map<String, String>>();

		try {
			ps = con.prepareStatement(
					"SELECT coursetopic_id,topic_name,shortdescription FROM coursetopics WHERE user_id=?");

			ps.setString(1, userId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> topicMap = new HashMap<String, String>();
				topicMap.put("coursetopic_id", rs.getString("coursetopic_id"));
				topicMap.put("topic_name", rs.getString("topic_name"));
				topicMap.put("shortdescription", rs.getString("shortdescription"));

				topicList.add(topicMap);

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return topicList;
	}

	public Map<String, String> getCourseTopicByTopicId(String topicId) {
		final String METHODNAME = "getCourseTopicByTopicId";
		LOGGER.log(Level.INFO, "logging:  topicId : {0} ", new Object[] { topicId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> topicMap = new HashMap<String, String>();

		try {
			ps = con.prepareStatement("SELECT topic_name,shortdescription FROM coursetopics WHERE coursetopic_id=?");
			ps.setString(1, topicId);

			rs = ps.executeQuery();
			while (rs.next()) {
				topicMap.put("topic_name", rs.getString("topic_name"));
				topicMap.put("shortdescription", rs.getString("shortdescription"));

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return topicMap;
	}

	public String getDocumentAdditionPath(String topicId) {
		final String METHODNAME = "getDocumentAdditionPath";
		LOGGER.log(Level.INFO, "logging:  topicId : {0} ", new Object[] { topicId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String documentAdditionPath = null;
		try {
			ps = con.prepareStatement(
					"select course,ct.topic_name from courses inner join coursetopics ct on(ct.course_id=courses.course_id) where ct.coursetopic_id=?");
			ps.setString(1, topicId);

			rs = ps.executeQuery();

			while (rs.next()) {
				documentAdditionPath = rs.getString("course") + "_" + rs.getString("topic_name");
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return documentAdditionPath;
	}

	public int insertDocuments(TopicUploadContentAction topicUploadContentAction) {
		final String METHODNAME = "insertDocuments";
		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;
		try {
			ps = con.prepareStatement(
					"insert IGNORE into documents(document_description,document_path,document_title,topic_id,user_id)values(?,?,?,?,?)");
			ps.setString(1, topicUploadContentAction.getDocumentTitle());
			ps.setString(2, topicUploadContentAction.getDocumentPath());
			ps.setString(3, topicUploadContentAction.getDocumentTitle());
			ps.setString(4, topicUploadContentAction.getCourseTopicChoice());
			ps.setString(5, topicUploadContentAction.getUserId());

			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	public int insertVideo(TopicUploadVideoAction topicUploadVideoAction) {
		final String METHODNAME = "getDocumentAdditionPath";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;
		try {
			ps = con.prepareStatement(
					"insert IGNORE into videos(topic_id,videos_description,videos_title,videos_path,user_id)values(?,?,?,?,?)");
			ps.setString(1, topicUploadVideoAction.getCourseTopicChoice());
			ps.setString(2, topicUploadVideoAction.getDocumentDescription());
			ps.setString(3, topicUploadVideoAction.getDocumentTitle());
			ps.setString(4, topicUploadVideoAction.getDocumentFileName());
			ps.setString(5, topicUploadVideoAction.getUserId());

			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	public List<Map<String, String>> getTopicDocumentList(String topicId) {
		final String METHODNAME = "getTopicDocumentList";
		LOGGER.log(Level.INFO, "logging:  topicId : {0} ", new Object[] { topicId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> documentList = new ArrayList<Map<String, String>>();

		try {
			ps = con.prepareStatement(
					"SELECT document_id,document_title,document_path FROM documents WHERE  topic_id=?");
			ps.setString(1, topicId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> documentMap = new HashMap<String, String>();
				documentMap.put("document_id", rs.getString("document_id"));
				documentMap.put("document_title", rs.getString("document_title"));
				documentMap.put("document_path", rs.getString("document_path"));
				documentList.add(documentMap);
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return documentList;
	}

	public List<Map<String, String>> getTopicVideoList(String topicId) {
		final String METHODNAME = "getTopicDocumentList";
		LOGGER.log(Level.INFO, "logging:  topicId : {0} ", new Object[] { topicId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> videoList = new ArrayList<Map<String, String>>();

		try {
			ps = con.prepareStatement("SELECT videos_id,videos_path,videos_description FROM videos WHERE topic_id=?");
			ps.setString(1, topicId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> videoMap = new HashMap<String, String>();
				videoMap.put("videos_id", rs.getString("videos_id"));
				videoMap.put("videos_path", rs.getString("videos_path"));
				videoMap.put("videos_description", rs.getString("videos_description"));
				videoList.add(videoMap);
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return videoList;
	}

	public List<Map<String, String>> getTopicExerciseList(String topicId) {
		final String METHODNAME = "getTopicExerciseList";
		LOGGER.log(Level.INFO, "logging:  topicId : {0} ", new Object[] { topicId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> exerciseList = new ArrayList<Map<String, String>>();

		try {
			ps = con.prepareStatement(
					"SELECT  question_id,question,optionA,optionB,optionC,optionD,correctOption,questionHint from exercises where topic_id=?");
			ps.setString(1, topicId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> exerciseMap = new HashMap<String, String>();
				exerciseMap.put("question_id", rs.getString("question_id"));
				exerciseMap.put("question", rs.getString("question"));
				exerciseMap.put("optionA", rs.getString("optionA"));
				exerciseMap.put("optionB", rs.getString("optionB"));
				exerciseMap.put("optionC", rs.getString("optionC"));
				exerciseMap.put("optionD", rs.getString("optionD"));
				exerciseMap.put("correctOption", rs.getString("correctOption"));
				exerciseMap.put("questionHint", rs.getString("questionHint"));

				exerciseList.add(exerciseMap);
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return exerciseList;
	}

	public int insertContent(TopicCreateContentAction topicCreateContentAction) {
		final String METHODNAME = "insertContent";
		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;
		try {
			ps = con.prepareStatement(
					"insert IGNORE into content(contents,content_title,topic_id,user_id)values(?,?,?,?)");
			ps.setString(1, topicCreateContentAction.getContents());
			ps.setString(2, topicCreateContentAction.getContentTitle());
			ps.setString(3, topicCreateContentAction.getCourseTopicChoice());
			ps.setString(4, topicCreateContentAction.getUserId());

			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;
	}

	public int insertExerciseQuestion(TopicCreateExerciseAction topicCreateExerciseAction) {
		final String METHODNAME = "insertExerciseQuestion";

		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;
		try {
			ps = con.prepareStatement(
					"insert  into exercises(question,optionA,optionB,optionC,optionD, correctOption,questionHint,topic_id,user_id)values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, topicCreateExerciseAction.getQuestion());
			ps.setString(2, topicCreateExerciseAction.getOptionA());
			ps.setString(3, topicCreateExerciseAction.getOptionB());
			ps.setString(4, topicCreateExerciseAction.getOptionC());
			ps.setString(5, topicCreateExerciseAction.getOptionD());
			ps.setString(6, topicCreateExerciseAction.getCorrectOption());
			ps.setString(7, topicCreateExerciseAction.getQuestionHint());
			ps.setString(8, topicCreateExerciseAction.getTopicId());
			ps.setString(9, topicCreateExerciseAction.getUserId());

			status = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	public Map<String, String> getExerciseQuestion(String questionId) {
		final String METHODNAME = "getTopicExerciseList";
		LOGGER.log(Level.INFO, "logging:  questionId : {0} ", new Object[] { questionId });
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> exerciseMap = new HashMap<String, String>();

		try {
			ps = con.prepareStatement(
					"SELECT question,optionA,optionB,optionC,optionD,correctOption,questionHint from exercises where question_id=?");
			ps.setString(1, questionId);

			rs = ps.executeQuery();
			while (rs.next()) {

				exerciseMap.put("question", rs.getString("question"));
				exerciseMap.put("optionA", rs.getString("optionA"));
				exerciseMap.put("optionB", rs.getString("optionB"));
				exerciseMap.put("optionC", rs.getString("optionC"));
				exerciseMap.put("optionD", rs.getString("optionD"));
				exerciseMap.put("correctOption", rs.getString("correctOption"));
				exerciseMap.put("questionHint", rs.getString("questionHint"));

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return exerciseMap;
	}

	public int updateExerciseQuestion(TopicUpdateExerciseAction topicUpdateExerciseAction) {
		final String METHODNAME = "getTopicExerciseList";

		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;
		try {
			ps = con.prepareStatement(
					"update exercises set question=?,optionA=?,optionB=?,optionC=?,optionD=?, correctOption=?,questionHint=? where question_id=?");
			ps.setString(1, topicUpdateExerciseAction.getQuestion());
			ps.setString(2, topicUpdateExerciseAction.getOptionA());
			ps.setString(3, topicUpdateExerciseAction.getOptionB());
			ps.setString(4, topicUpdateExerciseAction.getOptionC());
			ps.setString(5, topicUpdateExerciseAction.getOptionD());
			ps.setString(6, topicUpdateExerciseAction.getCorrectOption());
			ps.setString(7, topicUpdateExerciseAction.getQuestionHint());
			ps.setString(8, topicUpdateExerciseAction.getQuestionId());

			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	public int deleteExerciseQuestion(TopicDeleteExerciseAction topicDeleteExerciseAction) {
		final String METHODNAME = "getTopicExerciseList";

		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;
		try {
			ps = con.prepareStatement("delete from  exercises  where question_id=?");

			ps.setString(1, topicDeleteExerciseAction.getQuestionId());
			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		return status;

	}

	protected void createCourseTopic(CourseTopic courseTopic, Connection connection) throws SQLException {

		final String METHODNAME = "createCourseTopic";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"insert IGNORE into coursetopics(coursetopic_id,topic_name,shortdescription,subject_id,course_id,user_id)values(?,?,?,?,?,?)");
			preparedStatement.setLong(1, courseTopic.getTopicId());
			preparedStatement.setString(2, courseTopic.getTopicName());
			preparedStatement.setString(3, courseTopic.getTopicDescription());
			preparedStatement.setLong(4, courseTopic.getSubjectId());
			preparedStatement.setLong(5, courseTopic.getCourseId());
			preparedStatement.setLong(6, courseTopic.getUserId());
			preparedStatement.execute();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {

				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	protected void createCourseTopicType(CourseTopic courseTopic, Connection connection) throws SQLException {

		final String METHODNAME = "createCourseTopicType";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"insert into coursetopictype(coursetopic_id,type,path,version)values(?,?,?,?)");
			preparedStatement.setLong(1, courseTopic.getTopicId());
			preparedStatement.setString(2, courseTopic.getTopicType());
			preparedStatement.setString(3, courseTopic.getTopicPath());
			preparedStatement.setInt(4, courseTopic.getVersion());

			preparedStatement.execute();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {

				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

	}

	public CourseTopic fetchTopicById(CourseTopic courseTopic) {

		final String METHODNAME = "fetchTopicById";

		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(FETCH_TOPIC_BY_ID_SQL);
			preparedStatement.setLong(1, courseTopic.getTopicId());
			preparedStatement.setString(2, courseTopic.getTopicType());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Long topicId = resultSet.getLong("coursetopic_id");
				String topicName = resultSet.getString("topic_name");
				String topicDescription = resultSet.getString("shortdescription");
				Integer status = resultSet.getInt("status");
				String topicType = resultSet.getString("type");
				String path = resultSet.getString("path");
				Integer version = resultSet.getInt("version");

				courseTopic.setTopicId(topicId);
				courseTopic.setTopicName(topicName);
				courseTopic.setTopicDescription(topicDescription);
				courseTopic.setStatus(status);
				courseTopic.setTopicType(topicType);
				courseTopic.setTopicPath(path);
				courseTopic.setVersion(version);

			}
			LOGGER.logp(Level.ALL, CLASSNAME, METHODNAME, "Topic Object Fetched from DB Is " + courseTopic);

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return courseTopic;

	}
	
	
	public CourseTopic fetchTopicByName(CourseTopic courseTopic) {

		final String METHODNAME = "fetchTopicByName";

		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(FETCH_TOPIC_BY_NAME_SQL);
			preparedStatement.setString(1, courseTopic.getTopicName());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Long topicId = resultSet.getLong("coursetopic_id");
				String topicName = resultSet.getString("topic_name");
				String topicDescription = resultSet.getString("shortdescription");
				Integer status = resultSet.getInt("status");
				String topicType = resultSet.getString("type");
				String path = resultSet.getString("path");
				Integer version = resultSet.getInt("version");

				courseTopic.setTopicId(topicId);
				courseTopic.setTopicName(topicName);
				courseTopic.setTopicDescription(topicDescription);
				courseTopic.setStatus(status);
				courseTopic.setTopicType(topicType);
				courseTopic.setTopicPath(path);
				courseTopic.setVersion(version);

			}
			LOGGER.logp(Level.ALL, CLASSNAME, METHODNAME, "Topic Object Fetched from DB Is " + courseTopic);

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}

		return courseTopic;

	}

	
	

	public void createNewTopic(CourseTopic courseTopic) {
		final String METHODNAME = "createNewTopic";

		Connection connection = super.getConnection();

		if (connection != null) {

			try {
				connection.setAutoCommit(false);
				createCourseTopic(courseTopic, connection);
				createCourseTopicType(courseTopic, connection);
				connection.commit();
			} catch (SQLException e) {

				try {
					connection.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
                LOGGER.warning(" SQLException Occured   :: " + CLASSNAME + " " + METHODNAME + " " + e);
			} finally {

				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}

			}

		}

	}

	public void updateTopicVersion(CourseTopic courseTopic) {

		final String METHODNAME = "createNewTopic";

		Connection connection = super.getConnection();
		if (connection != null) {
			try {

				createCourseTopicType(courseTopic, connection);

			} catch (SQLException e) {

				LOGGER.warning(" SQLException Occured   :: " + CLASSNAME + " " + METHODNAME + " " + e);
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
				}
			}

		}

	}
	
	protected void updateTopicStatus(CourseTopic courseTopic,Connection connection) {
		
		final String METHODNAME = "updateTopicStatus";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(UPDATE_TOPIC_STATUS);
			preparedStatement.setInt(1, courseTopic.getStatus());
			preparedStatement.setLong(2, courseTopic.getTopicId());
			preparedStatement.setLong(3, courseTopic.getUserId());
			preparedStatement.execute();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {

				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		
	}
	
   
	
	protected void updateTopicTypeStatus(CourseTopic courseTopic,Connection connection) {
		
		final String METHODNAME = "updateTopicTypeStatus";

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(UPDATE_TOPIC_TYPE_STATUS);
			preparedStatement.setInt(1, courseTopic.getStatus());
			preparedStatement.setLong(2, courseTopic.getTopicId());
			preparedStatement.setInt(3, courseTopic.getVersion());
			preparedStatement.execute();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		} finally {

			try {

				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}

			} catch (SQLException e) {
				LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " + METHODNAME + " " + e);
			}
		}
		
	}
	
	
	public void updateTopic(CourseTopic courseTopic) {
		
		final String METHODNAME = "updateTopic";

		Connection connection = super.getConnection();

		if (connection != null) {

			try {
				connection.setAutoCommit(false);
				updateTopicStatus(courseTopic, connection);
				updateTopicTypeStatus(courseTopic, connection);
				connection.commit();
			} catch (SQLException e) {

				try {
					connection.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
                LOGGER.warning(" SQLException Occured   :: " + CLASSNAME + " " + METHODNAME + " " + e);
			} finally {

				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				}

			}

		}

	}

}
