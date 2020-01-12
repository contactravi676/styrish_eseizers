package com.styrish.users.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.styrish.comman.CommanUtility;
import com.styrish.connections.JDBCHelper;
import com.styrish.users.action.UserAddressAction;
import com.styrish.users.action.UserRegistrationAction;

public class UserRegistrationDaoImpl extends JDBCHelper{
	protected static final String CLASSNAME = "UserRegistrationDaoImpl";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	public int save(UserRegistrationAction userObject) {
		
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		int status = 0;

		try {
			ps = con.prepareStatement(
					"insert into userreg(firstname,mobile,username,PASSWORD,user_created,user_type)values(?,?,?,?,?,?)");
			ps.setString(1, userObject.getFirstName());
			ps.setString(2, userObject.getMobile());
			ps.setString(3, userObject.getEmail());
			ps.setString(4, userObject.getPassword());
			ps.setTimestamp(5, CommanUtility.getCurrentDate());
			ps.setString(6, userObject.getRole());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException e) {
				// LOGGER.warning(" SQLException Occured in finally :: " + CLASSNAME + " " +
				// METHODNAME + " " + e);
			}
		}
		return status;
	}

	public void activateUser(Long usersId) {
		Connection connection = JDBCHelper.getConnection();
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement("update userreg set user_status='1' where user_id=?");
			preparedStatement.setLong(1, usersId);
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				// LOGGER.warning(" SQLException Occured in finally :: " + CLASSNAME + " " +
				// METHODNAME + " " + e);
			}
		}

	}

	public boolean createInactiveUser(Map<String, String> userParameters) {
		boolean userCreated = false;
		Connection connection = JDBCHelper.getConnection();
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = connection.prepareStatement(
					"insert into userreg(firstname,mobile,username,PASSWORD,user_created,user_type,user_status)values(?,?,?,?,?,?,?)");
			preparedStatement.setString(1, (String) userParameters.get("fullName"));
			preparedStatement.setString(2, (String) userParameters.get("mobileNumber"));
			preparedStatement.setString(3, (String) userParameters.get("mobileNumber"));
			preparedStatement.setString(4, (String) userParameters.get("password"));
			preparedStatement.setTimestamp(5, CommanUtility.getCurrentDate());
			preparedStatement.setString(6, "Student");
			preparedStatement.setString(7, "0");
			preparedStatement.execute();
			userCreated = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				// LOGGER.warning(" SQLException Occured in finally :: " + CLASSNAME + " " +
				// METHODNAME + " " + e);
			}
		}
		return userCreated;
	}

	public String getUserId(String username) {
		final String METHODNAME = "getUserId";
		LOGGER.log(Level.INFO, "logging: username is {0} ", new Object[] { username});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String userId = null;

		try {
			ps = con.prepareStatement("SELECT user_id FROM userreg WHERE username=?");
			ps.setString(1, username);

			rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getString("user_id");

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
		return userId;
	}

	public boolean validateLogin(String username, String userpass) {
		final String METHODNAME = "validateLogin";
		LOGGER.log(Level.INFO, "logging: username is {0} and userpass {1}", new Object[] { username,userpass});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean userStatus=false;  

		
		try {
			ps = con.prepareStatement("SELECT * FROM userreg WHERE username=? AND password=? and user_status='1'");
			ps.setString(1, username);
			ps.setString(2, userpass);

			rs=ps.executeQuery();  
			userStatus=rs.next(); 

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
		return userStatus;

	}

	public Map<String, String> doLogin(String username, String userpass) {
		final String METHODNAME = "doLogin";
		LOGGER.log(Level.INFO, "logging: username is {0} and userpass {1}", new Object[] { username,userpass});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		Map<String, String> userMap = new HashMap<String, String>();

		try {
			ps = con.prepareStatement("Select user_id,firstname,username,user_type from userreg WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, userpass);
			rs = ps.executeQuery();
			while (rs.next()) {
				userMap.put("user_id", rs.getString("user_id"));
				userMap.put("firstName", rs.getString("firstname"));
				userMap.put("userName", rs.getString("username"));
				userMap.put("user_type", rs.getString("user_type"));
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



		return userMap;
	}

	public Map<String, String> fetchUserMap(String username) {
		final String METHODNAME = "fetchUserMap";
		LOGGER.log(Level.INFO, "logging: username is {0} ", new Object[] { username});
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> userMap = new HashMap<String, String>();

		try {
			ps = con.prepareStatement("Select user_id,firstname,username,user_type from userreg WHERE username=?");
			ps.setString(1, username);

			rs = ps.executeQuery();
			while (rs.next()) {
				userMap.put("user_id", rs.getString("user_id"));
				userMap.put("firstname", rs.getString("firstname"));
				userMap.put("userName", rs.getString("username"));
				userMap.put("user_type", rs.getString("user_type"));
			}

		} catch (SQLException e) {
	LOGGER.warning(" SQLException Occured :: " + CLASSNAME + " " + METHODNAME + " " + e);
		}

	
		return userMap;
	}

	public Map<String, String> checkIfAccountExists(String username) {

		Map<String, String> userMap = new HashMap<String, String>();
		Connection connection = JDBCHelper.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement("Select user_status,user_id from userreg WHERE username=?");
			preparedStatement.setString(1, username);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userMap.put("user_status", rs.getString("user_status"));
				userMap.put("user_id", rs.getString("user_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				// LOGGER.warning(" SQLException Occured in finally :: " + CLASSNAME + " " +
				// METHODNAME + " " + e);
			}
		}

		
		return userMap;
	}

	public int inserAddress(UserAddressAction userAddressAction) {
		

		Connection con = super.getConnection();
		PreparedStatement ps = null;

		int status = 0;

		try {
			ps = con.prepareStatement("insert into address(full_name,address1,address2,city,state,zipcode,country,user_id,user_photo,id_proof)values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, userAddressAction.getFullName());
			ps.setString(2, userAddressAction.getAddress1());
			ps.setString(3, userAddressAction.getAddress2());
			ps.setString(4, userAddressAction.getCity());
			ps.setString(5, userAddressAction.getState());
			ps.setString(6, userAddressAction.getZipcode());
			ps.setString(7, userAddressAction.getCountry());
			ps.setString(8, userAddressAction.getUserId());
			ps.setString(9, userAddressAction.getUserPhoto());
			ps.setString(10, userAddressAction.getIdProof());
			
			status = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public Map<String, String> getUserAddressMap(String userId) {
		final String METHODNAME = "getUserAddressMap";
		Map<String, String> addressMap = new HashMap<String, String>();
		Connection con = super.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"SELECT full_name,address1,address2,city,state,zipcode,country,user_photo,id_proof from address WHERE user_id=?");
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				addressMap.put("full_name", rs.getString("full_name"));
				addressMap.put("address1", rs.getString("address1"));
				addressMap.put("address2", rs.getString("address2"));
				addressMap.put("city", rs.getString("city"));
				addressMap.put("state", rs.getString("state"));
				addressMap.put("zipcode", rs.getString("zipcode"));
				addressMap.put("country", rs.getString("country"));
				addressMap.put("user_photo", rs.getString("user_photo"));
				addressMap.put("id_proof", rs.getString("id_proof"));
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



		return addressMap;
	}

}
