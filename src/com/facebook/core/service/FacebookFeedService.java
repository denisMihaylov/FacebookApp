package com.facebook.core.service;

import java.util.List;

import com.facebook.core.error.FacebookAppException;
import com.facebook.core.model.FacebookFeedEntry;

public interface FacebookFeedService {

	public List<FacebookFeedEntry> getFacebookFeedFromAllGroups(int userId) throws FacebookAppException;

	public void viewFeed(int userId, String feedId) throws FacebookAppException;

}
