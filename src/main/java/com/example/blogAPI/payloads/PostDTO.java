package com.example.blogAPI.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDTO {

	private int postId;
	private String postTitle;
	private String postContent;
	private String postImage;
	private Date addedDate;
	
	private UserDTO user;
	private CategoryDTO category;
	
	private Set<CommentDTO> comment = new HashSet<>();
	
	
	
	
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Set<CommentDTO> getComment() {
		return comment;
	}

	public void setComment(Set<CommentDTO> comment) {
		this.comment = comment;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public PostDTO() {
		super();
	}

	public PostDTO(int postId, String postTitle, String postContent, String postImage, Date addedDate, UserDTO user,
			CategoryDTO category, Set<CommentDTO> comment) {
		super();
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postImage = postImage;
		this.addedDate = addedDate;
		this.user = user;
		this.category = category;
		this.comment = comment;
	}



	
	
}
