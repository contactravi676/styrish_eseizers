package com.styrish.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.styrish.connections.JDBCHelper;
import com.styrish.users.databean.OTPDataBean;

public class OTPDAOImpl extends JDBCHelper {
	
	protected static final String CLASSNAME = "OTPDAOImpl";
	
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);
	
	protected static final String CREATE_OTP_SQL = "insert into otp (mobilenumber,otp,status,createddate) values (?,?,?,?)";
	
	protected static final String INVALIDATE_OTP_SQL = "update otp set status = 'X' where mobilenumber = ? and otp = ?";
	
	protected static final String FETCH_ACTIVE_OTP_SQL = "select otp,createddate from otp where mobilenumber=? and status='P'";
	
	public void createOTP(OTPDataBean loginOTPDataBean)  {
		
		final String METHODNAME = "createOTP";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		try {
		    preparedStatement = connection.prepareStatement(CREATE_OTP_SQL);
		    preparedStatement.setString(1, loginOTPDataBean.getMobileNumber());
		    preparedStatement.setString(2, loginOTPDataBean.getOtp());
		    preparedStatement.setString(3, loginOTPDataBean.getStatus());
		    preparedStatement.setTimestamp(4, new java.sql.Timestamp(loginOTPDataBean.getCreatedDate().getTime()),Calendar.getInstance());
			preparedStatement.execute();
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME,"One Time Password for Mobile {0}", loginOTPDataBean.getOtp());

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
	
	
	public OTPDataBean fetchOTP(OTPDataBean otpDataBean)  {
			
			final String METHODNAME = "fetchOTP";
			Connection connection = super.getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
			    preparedStatement = connection.prepareStatement(FETCH_ACTIVE_OTP_SQL);
			    preparedStatement.setString(1, otpDataBean.getMobileNumber());
			    resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					String otp =  resultSet.getString("otp");
					java.sql.Timestamp createdDate = resultSet.getTimestamp("createddate",Calendar.getInstance());
					otpDataBean.setOtp(otp);
					otpDataBean.setCreatedDate(new java.util.Date(createdDate.getTime()));
				}
				
				LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME,"One Time Password for Mobile {0}", otpDataBean.getOtp());
	
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
			return otpDataBean;
			
	}
	
	
	public OTPDataBean fetchExistingValidOTP(OTPDataBean otpDataBean)  {
		
		final String METHODNAME = "fetchExistingValidOTP";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		    preparedStatement = connection.prepareStatement(FETCH_ACTIVE_OTP_SQL);
		    preparedStatement.setString(1, otpDataBean.getMobileNumber());
		    resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String otp =  resultSet.getString("otp");
				java.sql.Timestamp createdDate = resultSet.getTimestamp("createddate",Calendar.getInstance());
				otpDataBean.setOtp(otp);
				otpDataBean.setCreatedDate(new java.util.Date(createdDate.getTime()));
			}
			
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME,"One Time Password for Mobile {0}", otpDataBean.getOtp());

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
		return otpDataBean;
	}
	
	public void invalidateOTP (OTPDataBean otpDataBean) {
		
		final String METHODNAME = "invalidateOTP";
		Connection connection = super.getConnection();
		PreparedStatement preparedStatement = null;
		try {
		    preparedStatement = connection.prepareStatement(INVALIDATE_OTP_SQL);
		    preparedStatement.setString(1, otpDataBean.getMobileNumber());
		    preparedStatement.setString(2, otpDataBean.getOtp());
		  
			preparedStatement.execute();
		    LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME,"One Time Password for Mobile {0}, Invalidated", otpDataBean.getMobileNumber());

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
	
	
	
	
	
	


