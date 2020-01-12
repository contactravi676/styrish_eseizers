package com.styrish.courses.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.styrish.connections.JDBCHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoursesDaoImpl extends JDBCHelper{
	protected static final String CLASSNAME = "CoursesDaoImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public List<Map<String,String>> getCourses() {
		final String METHODNAME = "getCourses";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		List<Map<String,String>> courseList =new ArrayList<Map<String,String>>();
		try {
			ps = con.prepareStatement("SELECT course_id,course,description FROM courses  ORDER BY course");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, String> courseMap = new HashMap<String, String>();
				courseMap.put("courseId", rs.getString("course_id"));
				courseMap.put("course", rs.getString("course"));
				courseMap.put("description", rs.getString("description"));
				courseList.add(courseMap);
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


		return courseList;
	}


	public List<Map<String,String>> getSubCourses(String courseId) {
		final String METHODNAME = "getSubCourses";
		 LOGGER.log(Level.INFO, "logging: course id is {0} ", new Object[] { courseId});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String,String>> subCourseList =new ArrayList<Map<String,String>>();
		try {
			ps = con.prepareStatement("SELECT subcourses_id,subcourse,description FROM subcourses WHERE  courses_id=?");
			ps.setString(1, courseId);

			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, String> subCourseMap = new HashMap<String, String>();
				subCourseMap.put("subcoursesId", rs.getString("subcourses_id"));
				subCourseMap.put("subcourse", rs.getString("subcourse"));
				subCourseMap.put("description", rs.getString("description"));
				subCourseList.add(subCourseMap);
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

		return subCourseList;
	}


	public List<Map<String,String>> getCourseSubjects(String courseId) {
		final String METHODNAME = "getCourseSubjects";
		 LOGGER.log(Level.INFO, "logging: course id is {0} ", new Object[] { courseId});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Map<String,String>> courseSubjectList =new ArrayList<Map<String,String>>();
		try {

			ps = con.prepareStatement("select subjects.subjects_name,subjects.subjects_description,coursesubject_id from subjects inner join coursesubject on(subjects.subjects_id=coursesubject.subjects_id) where course_id=?");
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> subjectMap = new HashMap<String, String>();
				subjectMap.put("coursesubject_id", rs.getString("coursesubject_id"));
				subjectMap.put("subjectname", rs.getString("subjects_name"));
				subjectMap.put("description", rs.getString("subjects_description"));
			    courseSubjectList.add(subjectMap);
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
		return courseSubjectList;
	}


	public List<Map<Long,String>> getCourseSubject(String courseId) {
		final String METHODNAME = "getCourseSubjects";
		 LOGGER.log(Level.INFO, "logging: course id is {0} ", new Object[] { courseId});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Map<Long,String>> courseSubjectList =new ArrayList<Map<Long,String>>();
		try {

			ps = con.prepareStatement("select subjects.subjects_name,subjects.subjects_description,coursesubject_id from subjects inner join coursesubject on(subjects.subjects_id=coursesubject.subjects_id) where course_id=?");
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<Long, String> subjectMap = new HashMap<Long, String>();
				subjectMap.put(rs.getLong("coursesubject_id"), rs.getString("subjects_name"));
				
			    courseSubjectList.add(subjectMap);
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
		return courseSubjectList;
	}






	public List<Map<String, String>> getCourseTopics(String courseId,String subjectId) {
		final String METHODNAME = "getCourseTopics";
		 LOGGER.log(Level.INFO, "logging: course id is {0} and subjectId : ", new Object[] { courseId,subjectId});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Map<String,String>> courseSubjectList =new ArrayList<Map<String,String>>();
		try {

			ps = con.prepareStatement("select coursetopic_id,topic_name,shortdescription from coursetopics where course_id=? and subject_id=?");
			ps.setString(1, courseId);
			ps.setString(2, subjectId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> courseTopicsMap = new HashMap<String, String>();
				courseTopicsMap.put("coursetopic_id",rs.getString("coursetopic_id"));
				courseTopicsMap.put("topic_name", rs.getString("topic_name"));
				courseTopicsMap.put("shortdescription", rs.getString("shortdescription"));
				courseSubjectList.add(courseTopicsMap);

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

		return courseSubjectList;
	}





}
