package com.facebook.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.facebook.core.db.DBConnectionProvider;
import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.FacebookFeedEntry;
import com.facebook.core.model.types.FacebookFeedEntryStatus;

public class FacebookFeedDao extends BaseDao {

	public List<FacebookFeedEntry> getUserFacebookFeedEntries(int userId) {
		List<FacebookFeedEntry> result = new ArrayList<>();

		return result;
	}

	public void viewFeed(int userId, String feedId, String content) throws FacebookAppException {
		Connection con = DBConnectionProvider.get();
		String query = "INSERT INTO public.facebook_feed_entry (id, content, user_id, status) values(?,?,?,?)";
		try (PreparedStatement registerStatement = con.prepareStatement(query)) {
			registerStatement.setString(1, feedId);
			registerStatement.setString(2, content);
			registerStatement.setInt(3, userId);
			registerStatement.setString(4, FacebookFeedEntryStatus.VIEWED.name());
			registerStatement.executeUpdate();
		} catch (SQLException e) {
			if (e.getMessage().contains("unique_feed_id")) {
				// Facebook entry is in the Database -> update status
				updateFeedStatus(feedId, FacebookFeedEntryStatus.VIEWED);
			} else {
				e.printStackTrace();
				throw new FacebookAppException(e.getMessage());
			}
		}
	}

	public void updateFeedStatus(String feedId, FacebookFeedEntryStatus newStatus) throws FacebookAppException {
		Connection con = DBConnectionProvider.get();
		String query = "UPDATE public.facebook_feed_entry SET status = ? WHERE id = ?";
		try (PreparedStatement registerStatement = con.prepareStatement(query)) {
			registerStatement.setString(1, newStatus.name());
			registerStatement.setString(2, feedId);
			registerStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacebookAppException(e.getMessage());
		}
	}

	public Map<String, FacebookFeedEntry> getFacebookFeedForUserAsMap(int userId) throws FacebookAppException {
		List<FacebookFeedEntry> feedEntries = getFacebookFeedForUser(userId);
		Map<String, FacebookFeedEntry> result = new HashMap<>();
		for (FacebookFeedEntry entry : feedEntries) {
			result.put(entry.getId(), entry);
		}
		return result;
	}

	public List<FacebookFeedEntry> getFacebookFeedForUser(int userId) throws FacebookAppException {
		String query = "SELECT * FROM public.facebook_feed_entry WHERE user_id = ?";
		Connection connection = DBConnectionProvider.get();
		List<FacebookFeedEntry> result = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(getFacebookFeedEntryFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacebookAppException("Error while getting all the feed for user from the database");
		}
		return result;
	}

	private FacebookFeedEntry getFacebookFeedEntryFromResultSet(ResultSet rs) throws SQLException {
		FacebookFeedEntry result = new FacebookFeedEntry();
		result.setId(rs.getString("id").trim());
		result.setContent(rs.getString("content").trim());
		result.setStatus(FacebookFeedEntryStatus.valueOf(rs.getString("status")));
		result.setUserId(rs.getInt("user_id"));
		return result;
	}
}
