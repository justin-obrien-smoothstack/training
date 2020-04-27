package com.ss.training.lms.versiontwo.business;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Justin O'Brien
 */
public class LMSService {
	
	protected Connection getConnection() {
		String propertiesPath = "resources/config/LMS.properties";
		Connection connection = null;
		Properties properties = new Properties();
		try (InputStream inputStream = new FileInputStream(propertiesPath)) {
			properties.load(inputStream);
		} catch (Exception e) {
			System.out.println("Error: Unable to load properties from " + propertiesPath);
			e.printStackTrace();
		}
		try {
			Class.forName((String) properties.get("driver"));
			connection = DriverManager.getConnection((String) properties.get("url"), (String) properties.get("user"),
					(String) properties.get("password"));
			connection.setAutoCommit(Boolean.FALSE);
		} catch (Exception e) {
			System.out.println("Error: Unable to connect to database.");
			e.printStackTrace();
		}
		return connection;
	}
}