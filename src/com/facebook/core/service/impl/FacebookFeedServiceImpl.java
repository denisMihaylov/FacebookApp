package com.facebook.core.service.impl;

import com.facebook.core.dao.DaoFactory;
import com.facebook.core.dao.FacebookFeedDao;
import com.facebook.core.service.FacebookFeedService;

public class FacebookFeedServiceImpl implements FacebookFeedService {
	
	private FacebookFeedDao getDao() {
		return DaoFactory.getFacebookFeedDao();
	}

}
