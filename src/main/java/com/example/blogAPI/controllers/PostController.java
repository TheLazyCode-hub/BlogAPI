package com.example.blogAPI.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blogAPI.payloads.APIresponse;
import com.example.blogAPI.payloads.PostDTO;
import com.example.blogAPI.payloads.PostResponse;
import com.example.blogAPI.services.FileService;
import com.example.blogAPI.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	FileService fileService;
	@Value("{project.image}")
	String path;

	// Create
	@RequestMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postdto, @PathVariable("userId") int userId,
			@PathVariable("categoryId") int categoryId) {
		PostDTO post = postService.createPost(postdto, userId, categoryId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}

	// Get all
	@RequestMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "search", required = false) String keyword,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "sortBy", required = false, defaultValue = "postTitle") String sortBy) {
		PostResponse posts = null;
		if (keyword != null) {
			posts = postService.searchPost(keyword, pageSize, pageNumber, sortBy);
		} else {
			posts = postService.getAllPosts(pageSize, pageNumber, sortBy);
		}

		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}

	// Get all by categoryid
	@RequestMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getAllPostByCategoryId(@PathVariable("categoryId") int categoryId) {
		List<PostDTO> posts = postService.getPostByCategoryId(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	// Get all by userId
	@RequestMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getAllPostByUserId(@PathVariable("userId") int userId) {
		List<PostDTO> posts = postService.getPostByUserId(userId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	// Get post by id
	@RequestMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") int postId) {
		PostDTO post = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}

	// Delete post by id
	@RequestMapping(method = RequestMethod.DELETE, value = "/posts/{postId}")
	public ResponseEntity<APIresponse> deletePostById(@PathVariable("postId") int postId) {
		postService.deletePost(postId);
		return new ResponseEntity<APIresponse>(new APIresponse("Post deleted", true), HttpStatus.OK);
	}

	// update post
	@RequestMapping(method = RequestMethod.PUT, value = "/posts/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postdto, @PathVariable("postId") int postId) {
		PostDTO post = postService.updatePost(postdto, postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}

	// Post image upload
	@RequestMapping(method=RequestMethod.POST, value="/posts/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") int postId) throws IOException {
		PostDTO post = postService.getPostById(postId);
	
		String fileName = fileService.uploadImage(path, image);
		post.setPostImage(fileName);
		PostDTO updatedPost = postService.updatePost(post, postId);
		return new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);  
	}
	
	@RequestMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
