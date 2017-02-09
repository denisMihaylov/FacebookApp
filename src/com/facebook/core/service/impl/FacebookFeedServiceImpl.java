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

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class FacebookFeedServiceImpl implements FacebookFeedService {

	@Override
	public List<FacebookFeedEntry> getFacebookFeedFromAllGroups(int userId) throws FacebookAppException {
		User user = getUserService().getUserById(userId);
		FacebookClient client = new FacebookClient(user.getAccessToken());
		try {
			ResponseList<Post> feed = client.test();
			System.out.println(feed);
		} catch (FacebookException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private FacebookFeedDao getDao() {
		return DaoFactory.getFacebookFeedDao();
	}
	
	private UserService getUserService() {
		return ServiceFactory.getUserService();
	}

}
