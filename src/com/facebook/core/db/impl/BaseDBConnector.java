package com.facebook.core.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.facebook.core.db.DBConnector;

public abstract class BaseDBConnector implements DBConnector {

	protected Connection connection;
	
	protected BaseDBConnector() {
		
	}

	public BaseDBConnector(String url, String username, String password) {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/facebookappdb", "dbadmin", "abcd1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
