package com.facebook.core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.facebook.core.db.DBConnectionProvider;
import com.facebook.core.model.User;
import com.facebook.core.model.types.Access;

public class UserDao extends BaseDao {

	public void addUser(User user) {

	}

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsers() {
		String query = "SELECT * FROM \"user\"";
		Connection connection = DBConnectionProvider.get();
		List<User> result = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				result.add(getUserFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private User getUserFromResultSet(ResultSet rs) throws SQLException {
		User result = new User();
		result.setId(rs.getInt("ID"));
		result.setFirstName(rs.getString("FIRST_NAME"));
		result.setLastName(rs.getString("LAST_NAME"));
		result.setEmail(rs.getString("EMAIL"));
		result.setPassword(rs.getString("PASSWORD"));
		result.setAccess(Access.valueOf(rs.getString("ACCESS")));
		return result;
	}

}
