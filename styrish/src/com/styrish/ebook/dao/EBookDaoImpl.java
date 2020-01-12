package com.styrish.ebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.styrish.connections.JDBCHelper;
import com.styrish.ebook.action.TutorEBookUploadAction;


public class EBookDaoImpl extends JDBCHelper{
	protected static final String CLASSNAME = "EBookDaoImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public String getEbookAdditionPath(String subcourseId) {

		final String METHODNAME = "getEbookAdditionPath";
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


	public int createEbook(TutorEBookUploadAction tutorEBookUploadAction) {
		final String METHODNAME = "createEbook";
		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;

		try {
			con = JDBCHelper.getConnection();
			ps = con.prepareStatement(
					"insert  into ebooks(course_id,subcourses_id,user_id,ebook_name,ebook_description,ebook_path)values(?,?,?,?,?,?)");
			ps.setString(1, tutorEBookUploadAction.getCourseId());
			ps.setString(2, tutorEBookUploadAction.getSubcourseId());
			ps.setString(3, tutorEBookUploadAction.getUserId());
			ps.setString(4, tutorEBookUploadAction.getDocumentTitle());
			ps.setString(5, tutorEBookUploadAction.getDocumentDescription());
			ps.setString(6, tutorEBookUploadAction.getDocumentPath());


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

