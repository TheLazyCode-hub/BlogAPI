package com.example.blogAPI.payloads;

public class CommentDTO {
	private int commentId;
	private String commentContent;
	private UserDTO user;
	
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public CommentDTO(int commentId, String commentContent) {
		super();
		this.commentId = commentId;
		this.commentContent = commentContent;
	}
	public CommentDTO() {
		super();
	}
	
}
