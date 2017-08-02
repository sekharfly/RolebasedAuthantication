package com.ajr.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Methods {
	String db_driver;
	String db_url;
	String username;
	String password;
	private static Methods instance = null;

	private Methods() {
	}

	public void name() throws IOException {
		InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		db_driver = properties.getProperty("db_driver");
		db_url = properties.getProperty("db_url");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(db_driver);
			con = DriverManager.getConnection(db_url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static Methods getInstance() {
		if (instance == null) {
			instance = new Methods();
		}
		return instance;
	}

}
