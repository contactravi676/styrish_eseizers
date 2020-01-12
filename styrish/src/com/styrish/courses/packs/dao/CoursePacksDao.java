package com.styrish.courses.packs.dao;

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
import com.styrish.courses.packs.databean.CoursePackageDetailsDataBean;

public class CoursePacksDao extends JDBCHelper{
	protected static final String CLASSNAME = "CoursePacksDao";
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	private static final String FETCH_PACKAGE_DETAILS_SQL="SELECT coursepacks_id,packname,packsdescription,packslongdescription,packimage_path,courseSubscription,"
			+ "pack_type,listprice,offerprice,package_contents FROM coursepacks WHERE coursepacks_id=? ";
	
public List<Map<String,String>> getCoursePack(String courseId) {
	final String METHODNAME = "getCoursePack";
	 LOGGER.log(Level.INFO, "logging: course id is {0}  ", new Object[] { courseId});
	Connection con = super.getConnection();
	PreparedStatement ps = null;
	ResultSet rs=null;
	List<Map<String,String>> packList =new ArrayList<Map<String,String>>();
		
		try {
			ps = con.prepareStatement("SELECT coursepacks_id,packname,packsdescription,packslongdescription,packimage_path,courseSubscription,pack_type,listprice,offerprice FROM coursepacks WHERE course_id=?");
			ps.setString(1, courseId);
			
			 rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> packMap = new HashMap<String, String>();
				packMap.put("coursepacks_id", rs.getString("coursepacks_id"));
				packMap.put("packname", rs.getString("packname"));
				packMap.put("packsdescription", rs.getString("packsdescription"));
				packMap.put("packslongdescription", rs.getString("packslongdescription"));
				packMap.put("packimage_path", rs.getString("packimage_path"));
				packMap.put("courseSubscription", rs.getString("courseSubscription"));
				packMap.put("pack_type", rs.getString("pack_type"));
				packMap.put("listprice", rs.getString("listprice"));
				packMap.put("offerprice", rs.getString("offerprice"));
				
				packList.add(packMap);
				
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
		return packList;
	} 



public String getMockPackCount(String subcourseId,String packType) {
	final String METHODNAME = "getMockPackCount";
	 LOGGER.log(Level.INFO, "logging: subcourseId is {0}  ", new Object[] { subcourseId});
	Connection con = super.getConnection();
	PreparedStatement ps = null;
	ResultSet rs=null;
	String mockpackCount=null;
		
		try {
			ps = con.prepareStatement("SELECT count(coursepacks_id) mockcount FROM coursepacks WHERE subcourse_id=? and pack_type=?");
			ps.setString(1, subcourseId);
			ps.setString(2, packType);
			
			 rs = ps.executeQuery();
			while (rs.next()) {
				mockpackCount=rs.getString("mockcount");
				
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
		return mockpackCount;
	} 



public List<Map<String,String>> getCoursePackBySubcourseId(String subcourseId,String packType) {
	final String METHODNAME = "getCoursePackBySubcourseId";
	 LOGGER.log(Level.INFO, "logging: course id is {0}  ", new Object[] { subcourseId});
	Connection con = super.getConnection();
	PreparedStatement ps = null;
	ResultSet rs=null;
	List<Map<String,String>> packList =new ArrayList<Map<String,String>>();
		
		try {
			ps = con.prepareStatement("SELECT coursepacks_id,packname,packsdescription,packslongdescription,packimage_path,courseSubscription,pack_type,listprice,offerprice FROM coursepacks WHERE subcourse_id=? and pack_type=?");
			ps.setString(1, subcourseId);
			ps.setString(2, packType);
			
			 rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, String> packMap = new HashMap<String, String>();
				packMap.put("coursepacks_id", rs.getString("coursepacks_id"));
				packMap.put("packname", rs.getString("packname"));
				packMap.put("packsdescription", rs.getString("packsdescription"));
				packMap.put("packslongdescription", rs.getString("packslongdescription"));
				packMap.put("packimage_path", rs.getString("packimage_path"));
				packMap.put("courseSubscription", rs.getString("courseSubscription"));
				packMap.put("pack_type", rs.getString("pack_type"));
				packMap.put("listprice", rs.getString("listprice"));
				packMap.put("offerprice", rs.getString("offerprice"));
				
				packList.add(packMap);
				
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
		return packList;
	} 



public CoursePackageDetailsDataBean fetchPackageDetails(Long coursepackId) {
	
	CoursePackageDetailsDataBean coursePackDetailsDataBean = new CoursePackageDetailsDataBean();
	
	Connection con = JDBCHelper.getConnection();
	PreparedStatement ps = null;
	ResultSet resultSet = null;
	
	try 
	{
		ps = con.prepareStatement(FETCH_PACKAGE_DETAILS_SQL);
		ps.setLong(1, coursepackId);
		resultSet = ps.executeQuery();
		
		while (resultSet.next()) {
			
			Long coursepacks_id = resultSet.getLong("coursepacks_id");
			String packName = resultSet.getString("packname");
			String packsdescription = resultSet.getString("packsdescription");
			String packslongdescription = resultSet.getString("packslongdescription");
			String packimage_path = resultSet.getString("packimage_path");
			String courseSubscription = resultSet.getString("courseSubscription");
			Double listprice = resultSet.getDouble("listprice");
			Double offerprice = resultSet.getDouble("offerprice");
			String package_contents = resultSet.getString("package_contents");
			
			coursePackDetailsDataBean.setCourseId(coursepacks_id);
			coursePackDetailsDataBean.setPackName(packName);
			coursePackDetailsDataBean.setPackDescription(packsdescription);
			coursePackDetailsDataBean.setPackLongDescription(packslongdescription);
			coursePackDetailsDataBean.setImagePath(packimage_path);
			coursePackDetailsDataBean.setCourseSubscription(courseSubscription);
			coursePackDetailsDataBean.setListPrice(listprice);
			coursePackDetailsDataBean.setOfferprice(offerprice);
			coursePackDetailsDataBean.setPackageContents(package_contents);
			
			
		}
		
		
	}catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	return coursePackDetailsDataBean;
	
	
	
	

}


}
