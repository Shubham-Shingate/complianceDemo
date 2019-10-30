package com.axis.compliance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestDbConn {


	public static Connection getConnection() {
		ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.US);
		Connection con = null;
		try {
			Class.forName(bundle.getString("DBDriver")).newInstance();
			DriverManager.setLoginTimeout(3);
			con = DriverManager.getConnection(bundle.getString("DBConnectURL"), bundle.getString("DBConnectUserName"),
					bundle.getString("DBConnectPassword"));
			System.out.println("============Connected============");
		} catch (Exception e) {
			System.out.print(e);
		}
		return con;
	}


}
