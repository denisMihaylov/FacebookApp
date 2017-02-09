package com.facebook.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.facebook.core.db.DBConnectionProvider;
import com.facebook.core.error.ExistingEmailException;
import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.User;
import com.facebook.core.model.types.Access;

public class UserDao extends BaseDao {

	public int addUser(User user) throws FacebookAppException {
		Connection con = DBConnectionProvider.get();
		String query = "INSERT INTO public.user " + "(first_name, last_name, email, password, access, access_token, facebook_user_id) "
				+ "values(?,?,?,?,?,?,?) RETURNING ID";
		try (PreparedStatement registerStatement = con.prepareStatement(query)) {
			registerStatement.setString(1, user.getFirstName());
			registerStatement.setString(2, user.getLastName());
			registerStatement.setString(3, user.getEmail());
			registerStatement.setString(4, user.getPassword());
			registerStatement.setString(5, user.getAccess().name());
			registerStatement.setString(6, user.getAccessToken());
			registerStatement.setLong(7, user.getFacebookUserId());
			ResultSet rs = registerStatement.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			if (e.getMessage().contains("unique_email")) {
				throw new ExistingEmailException("User already registered");
			} else {
				e.printStackTrace();
				throw new FacebookAppException(e.getMessage());
			}
		}
	}

	public User getUserById(int id) throws FacebookAppException {
		String query = "SELECT * FROM public.user WHERE id = ?";
		Connection connection = DBConnectionProvider.get();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return getUserFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacebookAppException("Error while getting user by id");
		}
	}
	
	public User getUserByFacebookId(long facebookId) throws FacebookAppException {
		String query = "SELECT * FROM public.user WHERE facebook_user_id = ?";
		Connection connection = DBConnectionProvider.get();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, facebookId);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			return getUserFromResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacebookAppException("Error while getting user by id");
		}
	}

	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsers() {
		String query = "SELECT * FROM public.user";
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
		result.setId(rs.getInt("id"));
		result.setFirstName(rs.getString("first_name"));
		result.setLastName(rs.getString("last_name"));
		result.setEmail(rs.getString("email"));
		result.setPassword(rs.getString("password"));
		result.setAccess(Access.valueOf(rs.getString("access")));
		result.setAccessToken(rs.getString("access_token"));
		result.setFacebookUserId(rs.getLong("facebook_user_id"));
		return result;
	}

}
