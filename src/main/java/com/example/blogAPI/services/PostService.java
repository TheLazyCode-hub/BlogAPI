package com.example.blogAPI.services;

import java.util.List;

import com.example.blogAPI.payloads.PostDTO;
import com.example.blogAPI.payloads.PostResponse;

public interface PostService {
	// getAllPost------------------------ done
	public PostResponse getAllPosts(int pageSize,int pageNumber,String sortBy);

	// getPostById ----------------- done
	public PostDTO getPostById(int postId);

	// getPostByUserId ----------------- done
	public List<PostDTO> getPostByUserId(int userId);

	// getPostByCategoryId ----------------- done
	public List<PostDTO> getPostByCategoryId(int categoryId);

	// createPost----------------- done
	public PostDTO createPost(PostDTO postdto, int userId, int categoryId);

	// deletePost ----------- done
	public void deletePost(int postId);

	// updatePost ----------------- done
	public PostDTO updatePost(PostDTO postdto, int postId);

	// search  ----------- done
	public PostResponse searchPost(String keyword,int pageSize,int pageNumber,String sortBy);

}
