package com.facebook.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.facebook.core.dao.DaoFactory;
import com.facebook.core.dao.FacebookFeedDao;
import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.FacebookFeedEntry;
import com.facebook.core.model.User;
import com.facebook.core.oauth.FacebookClient;
import com.facebook.core.service.FacebookFeedService;
import com.facebook.core.service.ServiceFactory;
import com.facebook.core.service.UserService;

import facebook4j.FacebookException;
import facebook4j.Group;
import facebook4j.Post;
import facebook4j.ResponseList;

public class FacebookFeedServiceImpl implements FacebookFeedService {

	@Override
	public List<FacebookFeedEntry> getFacebookFeedFromAllGroups(int userId) throws FacebookAppException {
		User user = getUserService().getUserById(userId);
		FacebookClient client = new FacebookClient(user.getAccessToken());
		try {
			List<FacebookFeedEntry> result = new ArrayList<>();
			ResponseList<Group> groups = client.getAllUserGroups();
			for (Group group : groups) {
				ResponseList<Post> groupFeed = client.getGroupFeed(group.getId());
				for (Post post : groupFeed) {
					result.add(new FacebookFeedEntry(post));
				}
			}
			return result;
		} catch (FacebookException e) {
			e.printStackTrace();
			throw new FacebookAppException("Error while getting feed");
		}
	}

	private FacebookFeedDao getDao() {
		return DaoFactory.getFacebookFeedDao();
	}

	private UserService getUserService() {
		return ServiceFactory.getUserService();
	}

}
