package com.styrish.connections;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCHelper {

  public static Connection getConnection() {

//	System.out.println("-------- MySQL JDBC Connection Testing ------------");
	Connection connection = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		
	}

//	System.out.println("MySQL JDBC Driver Registered! ha ha");
	

	try {
		//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seizerstech","root", "");
		//connection = DriverManager.getConnection("jdbc:mysql://64.62.143.92:3306/eseizers_styrish?serverTimezone=UTC","eseizers_styrish", "styrish@123");
		connection = DriverManager.getConnection("jdbc:mysql://43.231.124.71:3306/eseizers_styrish?serverTimezone=UTC","eseizers_styrish", "styrish@123");
		System.out.println("connection success:::::::::::::::::::");
	} catch (SQLException e) {
		
		e.printStackTrace();
		return connection;
	}

	if (connection != null) {
//		System.out.println("You made it, take control your database now!");
	} else {
		
	}
	return connection;
  }
}
