package com.styrish.mockvideo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.styrish.connections.JDBCHelper;
import com.styrish.mockvideo.action.TutorMockVideoUploadAction;

public class MockVideoDaoImpl extends JDBCHelper{
	protected static final String CLASSNAME = "MockVideoDaoImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public String getMockVideoAdditionPath(String subcourseId) {

		final String METHODNAME = "getMockVideoAdditionPath";
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String videoAdditionPath=null;
		try {
			ps = con.prepareStatement("select co.course course,sub.subcourse subcourse from courses co inner join subcourses sub on(sub.courses_id=co.course_id) where sub.subcourses_id=?");
			ps.setString(1, subcourseId);

			rs = ps.executeQuery();

			while (rs.next()) {
				videoAdditionPath=rs.getString("course")+"_"+rs.getString("subcourse");
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
		return videoAdditionPath;
	}


	public int createMockVideo(TutorMockVideoUploadAction tutorMockVideoUploadAction) {
		final String METHODNAME = "createMockVideo";
		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;

		try {
			con = JDBCHelper.getConnection();
			ps = con.prepareStatement(
					"insert  into mockvideos(course_id,subcourses_id,user_id,mockvideos_name,mockvideos_path,mockvideos_description)values(?,?,?,?,?,?)");
			ps.setString(1, tutorMockVideoUploadAction.getCourseId());
			ps.setString(2, tutorMockVideoUploadAction.getSubcourseId());
			ps.setString(3, tutorMockVideoUploadAction.getUserId());
			ps.setString(4, tutorMockVideoUploadAction.getDocumentTitle());
			ps.setString(5, tutorMockVideoUploadAction.getDocumentPath());
			ps.setString(6, tutorMockVideoUploadAction.getDocumentDescription());

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
