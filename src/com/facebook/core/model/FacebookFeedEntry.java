package com.facebook.core.model;

import java.util.Date;

import com.facebook.core.model.types.FacebookFeedEntryStatus;

import facebook4j.Post;

public class FacebookFeedEntry {

	private String id;
	private String content;
	private FacebookFeedEntryStatus status = FacebookFeedEntryStatus.NEW;
	private int userId;
	private int likesCount;
	private int commentsCount;
	private int sharesCount;
	private Date postDate;
	private String pictureUrl;

	public FacebookFeedEntry() {
		super();
	}

	public FacebookFeedEntry(Post post) {
		this.id = post.getId();
		if (notNullOrEmpty(post.getStory())) {
			this.content = post.getStory();
		} else if (notNullOrEmpty(post.getMessage())) {
			this.content = post.getMessage();
		} else if (post.getPicture() != null) {
			this.content = "Posted a picture";
		} else {
			this.content = "Unrecognized content";
		}
		this.content = post.getFrom().getName() + ": " + this.content;
		this.likesCount = getIntValue(post.getReactions().getSummary().getTotalCount());
		this.commentsCount = getIntValue(post.getComments().getSummary().getTotalCount());
		this.sharesCount = getIntValue(post.getSharesCount());
		this.postDate = post.getCreatedTime();
		if (post.getPicture() != null) {
			this.pictureUrl = post.getPicture().toString();
		}
	}

	private boolean notNullOrEmpty(String value) {
		return value != null && !value.isEmpty();
	}

	private int getIntValue(Integer value) {
		return value == null ? 0 : value;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public int getSharesCount() {
		return sharesCount;
	}

	public void setSharesCount(int sharesCount) {
		this.sharesCount = sharesCount;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

}
