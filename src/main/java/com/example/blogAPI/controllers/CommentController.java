package com.example.blogAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogAPI.payloads.APIresponse;
import com.example.blogAPI.payloads.CommentDTO;
import com.example.blogAPI.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(method = RequestMethod.POST, value="/post/{postId}/user/{userId}/comment")
	public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDto, @PathVariable int postId, @PathVariable int userId){
		CommentDTO comment = commentService.createComment(commentDto, postId, userId); 
		return new ResponseEntity<CommentDTO>(comment,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/comment/{commentId}")
	public ResponseEntity<APIresponse> deleteComment(@PathVariable int commentId){
		commentService.deleteComment(commentId);
		return new ResponseEntity<APIresponse>(new APIresponse("Comment deleted", true),HttpStatus.OK);
	}
}
