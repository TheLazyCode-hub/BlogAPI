package com.example.blogAPI.services;

import com.example.blogAPI.payloads.CommentDTO;

public interface CommentService {
	CommentDTO createComment(CommentDTO commentdto, int postId, int userId);
	void deleteComment(int commentId);
}
