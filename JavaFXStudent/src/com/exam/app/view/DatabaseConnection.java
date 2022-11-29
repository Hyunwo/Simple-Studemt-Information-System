package com.exam.app.view;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	public static Connection databaseLink;
	
	public static Connection getDBConnection() {
		String databaseName = "stddb";
		String databaseUser = "root";
		String databasePassword = "jdhw5140~";
		String url = "jdbc:mysql://localhost:3306/" + databaseName;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return databaseLink;
	}
		
}
