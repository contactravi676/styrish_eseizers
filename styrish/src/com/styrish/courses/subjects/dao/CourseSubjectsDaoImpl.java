package com.styrish.courses.subjects.dao;

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

public class CourseSubjectsDaoImpl extends JDBCHelper{
	protected static final String CLASSNAME = "CourseSubjectsDaoImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public List<Map<String,String>> getCourseSubjectList(String courseId) {
		final String METHODNAME = "getCourseSubjectList";
		 LOGGER.log(Level.INFO, "logging: course id is {0}  ", new Object[] { courseId});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;

		List<Map<String,String>> courseSubjectList =new ArrayList<Map<String,String>>();

		try {
			ps = con.prepareStatement("SELECT coursesubject_id,subjectname,description,longdescription FROM coursesubject WHERE course_id==?");
			ps.setString(1, courseId);

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> courseSubjectMap = new HashMap<String, String>();
				courseSubjectMap.put("coursesubject_id", rs.getString("coursesubject_id"));
				courseSubjectMap.put("subjectname", rs.getString("subjectname"));
				courseSubjectMap.put("description", rs.getString("description"));
				courseSubjectMap.put("longdescription", rs.getString("longdescription"));

				courseSubjectList.add(courseSubjectMap);

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
