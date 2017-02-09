package com.facebook.core.model;

import com.facebook.core.model.types.FacebookFeedEntryStatus;

import facebook4j.Post;

public class FacebookFeedEntry {
	
	private String id;
	private String message;
	private FacebookFeedEntryStatus status;
	
	public FacebookFeedEntry(Post post) {
		this.id = post.getId();
		this.message = post.getMessage();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FacebookFeedEntryStatus getStatus() {
		return status;
	}

	public void setStatus(FacebookFeedEntryStatus status) {
		this.status = status;
	}

}
