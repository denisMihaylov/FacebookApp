package com.facebook.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

	private static Connection connection;

	public static Connection get() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/facebookappdb", "dbadmin", "abcd1234");
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
