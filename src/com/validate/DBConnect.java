package com.validate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class DBConnect {

	private static ResourceBundle rb = ResourceBundle.getBundle("application", Locale.US);
	final static Logger logger = Logger.getLogger(ComplianceRules.class.getName());

	public static Connection getConnection() {
		
		Connection con =null;
		try {
			Class.forName(rb.getString("DBDriver"));
			con = DriverManager.getConnection(rb.getString("DBConnectURL"),
					rb.getString("DBConnectUserName"), rb.getString("DBConnectPassword"));
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
}