package com.styrish.footer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.styrish.connections.JDBCHelper;
import com.styrish.footer.action.ContactUsAction;


public class FooterLinksDAOImpl extends JDBCHelper {
	protected static final String CLASSNAME = "FooterLinksDAOImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	protected static final String CREATE_CONTACTUS_SQL = "insert into contactus (name,mobile,email,message)values(?,?,?,?) ";
			
	
	
	
	public void createContactUs(ContactUsAction contactUsAction) {
		final String METHODNAME = "createContactUs";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		try {
           
		    preparedStatement = connection.prepareStatement(CREATE_CONTACTUS_SQL);
			preparedStatement.setString(1, contactUsAction.getName());
			preparedStatement.setString(2, contactUsAction.getMobile());
			preparedStatement.setString(3, contactUsAction.getEmail());
			preparedStatement.setString(4, contactUsAction.getMessage());
			
			
			preparedStatement.execute();
			

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

	}



}
