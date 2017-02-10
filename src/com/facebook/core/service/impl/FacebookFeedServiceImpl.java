package com.facebook.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.facebook.core.dao.DaoFactory;
import com.facebook.core.dao.FacebookFeedDao;
import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.FacebookFeedEntry;
import com.facebook.core.model.User;
import com.facebook.core.model.types.FacebookFeedEntryStatus;
import com.facebook.core.oauth.FacebookClient;
import com.facebook.core.service.FacebookFeedService;
import com.facebook.core.service.ServiceFactory;
import com.facebook.core.service.UserService;

import facebook4j.FacebookException;
import facebook4j.Group;
import facebook4j.Post;

public class FacebookFeedServiceImpl implements FacebookFeedService {

	@Override
	public List<FacebookFeedEntry> getFacebookFeedFromAllGroups(int userId) throws FacebookAppException {
		User user = getUserService().getUserById(userId);
		FacebookClient client = new FacebookClient(user.getAccessToken());
		try {
			// Get all the feed from the database
			Map<String, FacebookFeedEntry> entriesMap = getDao().getFacebookFeedForUserAsMap(userId);
			List<FacebookFeedEntry> result = new ArrayList<>();
			List<Group> groups = client.getAllUserGroups();
			for (Group group : groups) {
				List<Post> groupFeed = client.getGroupFeed(group.getId());
				for (Post post : groupFeed) {
					if (post.getId().equals("1410914212269973_1507479655946761")) {
						System.out.println("TEST");
					}
					FacebookFeedEntry feedEntry = new FacebookFeedEntry(post);
					FacebookFeedEntry fromdb = entriesMap.get(feedEntry.getId());
					if (fromdb == null || fromdb.getStatus() != FacebookFeedEntryStatus.DELETED) {
						result.add(feedEntry);
						if (fromdb != null) {
							feedEntry.setStatus(fromdb.getStatus());
						}
					}
				}
			}

			return result;
		} catch (FacebookException e) {
			e.printStackTrace();
			throw new FacebookAppException("Error while getting feed");
		}
	}

	@Override
	public void viewFeed(int userId, String feedId) throws FacebookAppException {
		User user = getUserService().getUserById(userId);
		FacebookClient client = new FacebookClient(user.getAccessToken());
		try {
			Post post = client.getPost(feedId);
			String content = post.getStory();
			if (content == null || content.isEmpty()) {
				content = post.getMessage();
			}
			getDao().viewFeed(userId, feedId, content);
		} catch (FacebookException e) {
			e.printStackTrace();
			throw new FacebookAppException("Error while viewing post");
		}
	}

	private FacebookFeedDao getDao() {
		return DaoFactory.getFacebookFeedDao();
	}

	private UserService getUserService() {
		return ServiceFactory.getUserService();
	}

}
