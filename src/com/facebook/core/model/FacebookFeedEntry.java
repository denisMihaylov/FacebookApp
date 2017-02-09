package com.facebook.core.model;

import com.facebook.core.model.types.FacebookFeedEntryStatus;

import facebook4j.Post;

public class FacebookFeedEntry {

	private String id;
	private String content;
	private FacebookFeedEntryStatus status = FacebookFeedEntryStatus.NEW;

	public FacebookFeedEntry(Post post) {
		this.id = post.getId();
		if (post.getStory() == null || post.getStory().isEmpty()) {
			this.content = post.getMessage();
		} else {
			this.content = post.getStory();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FacebookFeedEntryStatus getStatus() {
		return status;
	}

	public void setStatus(FacebookFeedEntryStatus status) {
		this.status = status;
	}

}
