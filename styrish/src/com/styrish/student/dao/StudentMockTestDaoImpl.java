package com.styrish.student.dao;

    import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
   import java.util.*;
   import java.util.logging.Logger;

	import com.styrish.connections.JDBCHelper;
import com.styrish.student.action.StudentMockTestPaperSubmitAction;
	


	public class StudentMockTestDaoImpl extends JDBCHelper{
		protected static final String CLASSNAME = "StudentMockTestDaoImpl";
		protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);
		
		protected static final String FETCH_MOCKTEST_BY_STUDENT="SELECT subcourses.subcourse,mocktest_id, mocktest_name,noOfQuestions,testTime from mocktest left join subcourses on(subcourses.subcourses_id=mocktest.subcourses_id) where mocktest_status='S' and course_id=?";
		protected static final String SUBMIT_EXAM_BY_STUDENT="insert into StudentExam(user_id,course_id,mocktest_id,question_id,correct_option,studentexam_status)values(?,?,?,?,?,?)";
		

		public List<Map<String,String>> getMockTestByStudent(String courseId) {

			final String METHODNAME = "getMockTestByStudent";
			Connection con = super.getConnection();
			PreparedStatement ps = null;
			ResultSet rs=null;
			List<Map<String,String>> mockTestList =new ArrayList<Map<String,String>>();
			try {
				ps = con.prepareStatement(FETCH_MOCKTEST_BY_STUDENT);
				ps.setString(1, courseId);
				
               rs = ps.executeQuery();

				while (rs.next()) {
					Map<String,String> mocktestMap=new HashMap<String,String>();
					mocktestMap.put("subcourse", rs.getString("subcourse"));
					mocktestMap.put("mocktest_id", rs.getString("mocktest_id"));
					mocktestMap.put("mocktest_name", rs.getString("mocktest_name"));
					mocktestMap.put("noOfQuestions", rs.getString("noOfQuestions"));
					mocktestMap.put("testTime", rs.getString("testTime"));
				
					mockTestList.add(mocktestMap);
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


	/*
	 * public int submitExamByStudent(StudentMockTestPaperSubmitAction
	 * studentMockTestPaperSubmitAction) {
	 * 
	 * final String METHODNAME = "submitExamByStudent"; Connection con =
	 * super.getConnection(); PreparedStatement ps = null; ResultSet rs=null;
	 * List<Map<String,String>> mockTestList =new ArrayList<Map<String,String>>();
	 * try { ps = con.prepareStatement(SUBMIT_EXAM_BY_STUDENT); // ps.setString(1,
	 * courseId);
	 * 
	 * rs = ps.executeQuery();
	 * 
	 * while (rs.next()) { Map<String,String> mocktestMap=new
	 * HashMap<String,String>(); mocktestMap.put("subcourse",
	 * rs.getString("subcourse")); mocktestMap.put("mocktest_id",
	 * rs.getString("mocktest_id")); mocktestMap.put("mocktest_name",
	 * rs.getString("mocktest_name")); mocktestMap.put("noOfQuestions",
	 * rs.getString("noOfQuestions")); mocktestMap.put("testTime",
	 * rs.getString("testTime"));
	 * 
	 * mockTestList.add(mocktestMap); }
	 * System.out.println("mockTestList::"+mockTestList); } catch (SQLException e) {
	 * LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME +
	 * " " + e); } finally {
	 * 
	 * try { if (con != null) { con.close(); } if (ps != null) { ps.close(); }
	 * 
	 * } catch (SQLException e) {
	 * LOGGER.warning(" SQLException Occured in finally  :: " + CLASSNAME + " " +
	 * METHODNAME + " " + e); } } return null; }
	 * 
	 */

}
