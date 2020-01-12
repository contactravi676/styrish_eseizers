package com.styrish.mocktest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.styrish.connections.JDBCHelper;
import com.styrish.mocktest.action.TutorMockTestAddQuestionsAction;
import com.styrish.mocktest.action.TutorMockTestCreateAction;
import com.styrish.mocktest.action.TutorMockTestUpdateQuestionsAction;

public class MockTestDaoImpl extends JDBCHelper{
	protected static final String CLASSNAME = "MockTestDaoImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);


	public List<Map<String,String>> getMockTest(String courseId,String mockStatus) {
		final String METHODNAME = "getMockTest";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;

		final String sqlPart1 = "Select mocktest_id,mocktest_name,noOfQuestions,testTime,testInstructions,mocktest_status from mocktest WHERE course_id=?";

		List<Map<String,String>> mockTestList=new ArrayList<Map<String,String>>();
		String statusStr=null;
		String finalSQL=null;
		if(mockStatus.contains("_")) {

			String [] mockTestStatuss = {"'P'","'S'"};
			statusStr = String.join(",", mockTestStatuss);
			finalSQL = new StringBuilder(sqlPart1).append(" ")
					.append("AND mocktest_status IN ").append(" ").append("(").append(statusStr).append(")").toString();
		}else{

			statusStr = String.join(",", mockStatus);	
			finalSQL=new StringBuilder(sqlPart1).append(" ")
					.append("AND mocktest_status IN ").append(" ").append("('").append(statusStr).append("')").toString();
		}

		try {


			ps = con.prepareStatement(finalSQL);
			ps.setString(1, courseId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String,String> mockTestMap =new HashMap<String,String>();
				mockTestMap.put("mocktest_id",rs.getString("mocktest_id"));
				mockTestMap.put("mocktest_name",rs.getString("mocktest_name"));
				mockTestMap.put("noOfQuestions",rs.getString("noOfQuestions"));
				mockTestMap.put("testTime",rs.getString("testTime"));
				mockTestMap.put("testInstructions",rs.getString("testInstructions"));
				mockTestMap.put("mocktest_status",rs.getString("mocktest_status"));
				mockTestList.add(mockTestMap);

			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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
		return mockTestList;
	} 



	public Map<String,String> getMockTestByName(String mockTestName,String subcourseId) {
		final String METHODNAME = "getMockTestByName";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;

		final String sql = "Select mocktest_id,mocktest_name,noOfQuestions,testTime,testInstructions from mocktest WHERE mocktest_name=? and subcourses_id=?";

		Map<String,String> mockTestMap =new HashMap<String,String>();
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1, mockTestName);
			ps.setString(2, subcourseId);

			rs = ps.executeQuery();

			while (rs.next()) {

				mockTestMap.put("mocktest_id",rs.getString("mocktest_id"));
				mockTestMap.put("mocktest_name",rs.getString("mocktest_name"));
				mockTestMap.put("noOfQuestions",rs.getString("noOfQuestions"));
				mockTestMap.put("testTime",rs.getString("testTime"));
				mockTestMap.put("testInstructions",rs.getString("testInstructions"));


			}
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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

		return mockTestMap;
	} 


	public Map<String,String> getMockTestByMockTestId(String mocktest_id) {
		final String METHODNAME = "getMockTestMockTestId";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;	
		final String sql = "Select mocktest_name,noOfQuestions,testTime,testInstructions from mocktest WHERE mocktest_id=?";

		Map<String,String> mockTestMap =new HashMap<String,String>();
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1, mocktest_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				mockTestMap.put("mocktest_name",rs.getString("mocktest_name"));
				mockTestMap.put("noOfQuestions",rs.getString("noOfQuestions"));
				mockTestMap.put("testTime",rs.getString("testTime"));
				mockTestMap.put("testInstructions",rs.getString("testInstructions"));


			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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


		return mockTestMap;
	} 


	public int createMockTest(TutorMockTestCreateAction tutorMockTestCreateAction) {
		final String METHODNAME = "createMockTest";
		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;

		try {

			ps = con.prepareStatement(
					"insert  into mocktest(course_id,mocktest_name,noOfQuestions,testTime,testInstructions,mocktest_status,subcourses_id,user_id)values(?,?,?,?,?,?,?,?)");
			ps.setString(1, tutorMockTestCreateAction.getCourseId());
			ps.setString(2, tutorMockTestCreateAction.getMocktestName());
			ps.setString(3, tutorMockTestCreateAction.getTotalQuestions());
			ps.setString(4, tutorMockTestCreateAction.getTotalTime());
			ps.setString(5, tutorMockTestCreateAction.getExamGuidlines());
			ps.setString(6, tutorMockTestCreateAction.getMocktestStatus());
			ps.setString(7, tutorMockTestCreateAction.getSubCourseId());
			ps.setString(8, tutorMockTestCreateAction.getUserId());
			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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

	public int updateMockTest(String mockTestId) {
		final String METHODNAME = "updateMockTest";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;

		try {

			ps = con.prepareStatement("update mocktest set mocktest_status='S' where mocktest_id=? ");

			ps.setString(1, mockTestId);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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


	// create mock test question
	public int createMockTestQuestion(TutorMockTestAddQuestionsAction tutorMockTestAddQuestionsAction) {
		final String METHODNAME = "createMockTest";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;

		try {

			ps = con.prepareStatement(
					"insert into mocktestquestions(mocktest_id,mockquestion,mockOptionA,mockOptionB,mockOptionC,mockOptionD,mockCorrectOption,mockHint)values(?,?,?,?,?,?,?,?)");
			ps.setString(1, tutorMockTestAddQuestionsAction.getMockTestId());
			ps.setString(2, tutorMockTestAddQuestionsAction.getQuestion());
			ps.setString(3, tutorMockTestAddQuestionsAction.getOptionA());
			ps.setString(4, tutorMockTestAddQuestionsAction.getOptionB());
			ps.setString(5, tutorMockTestAddQuestionsAction.getOptionC());
			ps.setString(6, tutorMockTestAddQuestionsAction.getOptionD());
			ps.setString(7, tutorMockTestAddQuestionsAction.getCorrectOption());
			ps.setString(8, tutorMockTestAddQuestionsAction.getQuestionHint());

			status = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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


	public int updateMockTestQuestion(TutorMockTestUpdateQuestionsAction tutorMockTestUpdateQuestionsAction) {
		final String METHODNAME = "updateMockTestQuestion";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;

		try {
			ps = con.prepareStatement("update mocktestquestions set mockquestion=?,mockOptionA=?,mockOptionB=?,mockOptionC=?,mockOptionD=?,mockCorrectOption=?,mockHint=? where mocktestquestions_id=? ");
			ps.setString(1, tutorMockTestUpdateQuestionsAction.getQuestion());
			ps.setString(2, tutorMockTestUpdateQuestionsAction.getOptionA());
			ps.setString(3, tutorMockTestUpdateQuestionsAction.getOptionB());
			ps.setString(4, tutorMockTestUpdateQuestionsAction.getOptionC());
			ps.setString(5, tutorMockTestUpdateQuestionsAction.getOptionD());
			ps.setString(6, tutorMockTestUpdateQuestionsAction.getCorrectOption());
			ps.setString(7, tutorMockTestUpdateQuestionsAction.getQuestionHint());
			ps.setString(8, tutorMockTestUpdateQuestionsAction.getQuestionId());

			status = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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


	public List<Map<String,String>> getMockTestQuestions(String mockTestId) {
		final String METHODNAME = "updateMockTestQuestion";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		List<Map<String,String>> questionList =new ArrayList<Map<String,String>>();

		try {
			ps = con.prepareStatement("Select mocktestquestions_id,mockquestion,mockOptionA,mockOptionB,mockOptionC,mockOptionD,mockCorrectOption,mockHint from mocktestquestions WHERE mocktest_id=?");
			ps.setString(1, mockTestId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> questionMap = new HashMap<String, String>();
				questionMap.put("mocktestquestions_id",rs.getString("mocktestquestions_id"));
				questionMap.put("mockquestion",rs.getString("mockquestion"));
				questionMap.put("mockOptionA",rs.getString("mockOptionA"));
				questionMap.put("mockOptionB",rs.getString("mockOptionB"));
				questionMap.put("mockOptionC",rs.getString("mockOptionC"));
				questionMap.put("mockOptionD",rs.getString("mockOptionD"));
				questionMap.put("mockCorrectOption",rs.getString("mockCorrectOption"));
				questionMap.put("mockHint",rs.getString("mockHint"));

				questionList.add(questionMap);
			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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
		return questionList;
	} 
	
	
	


	public Map<String,String> getQuestion(String questionId) {
		final String METHODNAME = "getQuestion";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		Map<String, String> questionMap = new HashMap<String, String>();

		try {
			ps = con.prepareStatement("Select mockquestion,mockOptionA,mockOptionB,mockOptionC,mockOptionD,mockCorrectOption,mockHint from mocktestquestions WHERE mocktestquestions_id=?");
			ps.setString(1, questionId);

			rs = ps.executeQuery();
			while (rs.next()) {

				questionMap.put("mockquestion",rs.getString("mockquestion"));
				questionMap.put("mockOptionA",rs.getString("mockOptionA"));
				questionMap.put("mockOptionB",rs.getString("mockOptionB"));
				questionMap.put("mockOptionC",rs.getString("mockOptionC"));
				questionMap.put("mockOptionD",rs.getString("mockOptionD"));
				questionMap.put("mockCorrectOption",rs.getString("mockCorrectOption"));
				questionMap.put("mockHint",rs.getString("mockHint"));


			}

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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
		return questionMap;
	} 


	public int deleteQuestion(String questionId) {
		final String METHODNAME = "deleteQuestion";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;

		try {
			ps = con.prepareStatement("delete from mocktestquestions WHERE mocktestquestions_id=?");
			ps.setString(1, questionId);
			status = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}
		finally {

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
}
