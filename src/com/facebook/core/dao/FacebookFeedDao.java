package com.facebook.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

}
