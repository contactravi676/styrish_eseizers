package com.styrish.courses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.styrish.connections.JDBCHelper;
public class CourseTopicDaoImpl {
	
	public Map<String, List> getCourseTopicsVideos(String topicId) {

		Map<String, List> courseTopicVideoMap = new HashMap<String, List>();
		try {

			Connection con = JDBCHelper.getConnection();

			PreparedStatement ps = con.prepareStatement("select videos_title,videos_path,videos_description from videos where topic_id=?");
			ps.setString(1, topicId);
			ResultSet rs = ps.executeQuery();
			List videopathList=new ArrayList();
			List videoTitleList=new ArrayList();
			List videoDescriptionList=new ArrayList();
            while (rs.next()) {
            	videopathList.add(rs.getString("videos_path"));
            	videoTitleList.add(rs.getString("videos_title"));
            	videoDescriptionList.add(rs.getString("videos_description"));
            }
            courseTopicVideoMap.put("videoTitle", videoTitleList);
            courseTopicVideoMap.put("videosPath", videopathList);
            courseTopicVideoMap.put("videosDescription", videoDescriptionList);
            

		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return courseTopicVideoMap;
	}
	
	
	
	public Map<String, List> getCourseTopicsContent(String topicId) {

		Map<String, List> courseTopicContentMap = new HashMap<String, List>();
		try {

			Connection con = JDBCHelper.getConnection();

			PreparedStatement ps = con.prepareStatement("select document_title,document_path,document_description from documents where topic_id=?");
			ps.setString(1, topicId);
			ResultSet rs = ps.executeQuery();
			List contentpathList=new ArrayList();
			List contentTitleList=new ArrayList();
			List contentDescriptionList=new ArrayList();
            while (rs.next()) {
            	contentpathList.add(rs.getString("document_path"));
            	contentTitleList.add(rs.getString("document_title"));
            	contentDescriptionList.add(rs.getString("document_description"));
            }
            courseTopicContentMap.put("contentTitle", contentTitleList);
            courseTopicContentMap.put("contentPath", contentpathList);
            courseTopicContentMap.put("contentDescription", contentDescriptionList);
            

		} catch (Exception e) {
			e.printStackTrace();
		}

	

		return courseTopicContentMap;
	}
	
	
	
	
	public List getCourseTopicsExercise(String topicId) {

		List exercisesList=null;
		try {

			Connection con = JDBCHelper.getConnection();

			PreparedStatement ps = con.prepareStatement("select question,optionA,optionB,optionC,optionD,correctOption,questionHint from exercises where topic_id=?");
			ps.setString(1, topicId);
			ResultSet rs = ps.executeQuery();
			 exercisesList=new ArrayList();
			
            while (rs.next()) {
            	Map exerciseMap=new HashMap();
            	exerciseMap.put("question",rs.getString("question"));
            	exerciseMap.put("optionA",rs.getString("optionA"));
            	exerciseMap.put("optionB",rs.getString("optionB"));
            	exerciseMap.put("optionC",rs.getString("optionC"));
            	exerciseMap.put("optionD",rs.getString("optionD"));
            	exerciseMap.put("correctOption",rs.getString("correctOption"));
            	exerciseMap.put("questionHint",rs.getString("questionHint"));
            	exercisesList.add(exerciseMap);
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return exercisesList;
	}


}
