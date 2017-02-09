package com.facebook.core.oauth;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

public class FacebookClient {
	
	private static final String appId = "267142873716474";
	private static final String appSecret = "d6eb577be1755237ea54683cffdc09e0";
	
	Facebook facebook = new FacebookFactory().getInstance();
	
	public FacebookClient(String accessToken) {
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
	}
	
	public ResponseList<Post> test() throws FacebookException {
		return facebook.getHome();
	}
	
}
