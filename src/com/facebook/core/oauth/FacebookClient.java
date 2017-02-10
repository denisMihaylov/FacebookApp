package com.facebook.core.oauth;

import java.util.ArrayList;
import java.util.List;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Group;
import facebook4j.Paging;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class FacebookClient {

	private static final String appId = "267142873716474";
	private static final String appSecret = "d6eb577be1755237ea54683cffdc09e0";

	private Facebook facebook = new FacebookFactory().getInstance();
	private String accessToken;

	public FacebookClient(String accessToken) {
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
		this.accessToken = accessToken;
	}

	public List<Group> getAllUserGroups() throws FacebookException {
		List<Group> result = new ArrayList<>();
		ResponseList<Group> groups = facebook.getGroups();
		Paging<Group> paging;
		do {
			result.addAll(groups);
			paging = groups.getPaging();
		} while (paging != null && (groups = facebook.fetchNext(paging)) != null);
		return result;
	}

	public List<Post> getGroupFeed(String groupId) throws FacebookException {
		List<Post> result = new ArrayList<>();
		ResponseList<Post> groupFeed = facebook.getGroupFeed(groupId,
				new Reading().fields("message", "story", "created_time", "comments.limit(0).summary(true)", "reactions.limit(0).summary(true)", "from", "picture"));
		Paging<Post> paging;
		do {
			result.addAll(groupFeed);
			paging = groupFeed.getPaging();
		} while (paging != null && (groupFeed = facebook.fetchNext(paging)) != null); 
		return result;
	}

	public String renewAccessToken() throws FacebookException {
		return facebook.extendTokenExpiration(accessToken).getToken();
	}

	public Post getPost(String postId) throws FacebookException {
		return facebook.getPost(postId);
	}

}
