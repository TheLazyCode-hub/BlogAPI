package com.example.blogAPI.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.blogAPI.config.ModelsMapping;
import com.example.blogAPI.entities.Category;
import com.example.blogAPI.entities.Post;
import com.example.blogAPI.entities.User;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.payloads.PostDTO;
import com.example.blogAPI.payloads.PostResponse;
import com.example.blogAPI.repositories.CategoryRepository;
import com.example.blogAPI.repositories.PostRepository;
import com.example.blogAPI.repositories.UserRepository;
import com.example.blogAPI.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	ModelsMapping mappers;
	
	
	@Override
	public PostResponse getAllPosts(int pageSize,int pageNumber,String sortBy) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = postRepo.findAll(page);
		//List<Post> posts = postRepo.findAll();
		List<PostDTO> postdto = pagePost.stream().map((post) -> mappers.modelMapper().map(post, PostDTO.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postdto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDTO getPostById(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		PostDTO postDto = mappers.modelMapper().map(post, PostDTO.class);
		return postDto;
	}

	@Override
	public List<PostDTO> getPostByUserId(int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDTO> postdto = posts.stream().map((post) -> mappers.modelMapper().map(post, PostDTO.class)).collect(Collectors.toList());
		return postdto;
	}

	@Override
	public List<PostDTO> getPostByCategoryId(int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDTO> postDto = posts.stream().map((post) -> mappers.modelMapper().map(post, PostDTO.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public PostDTO createPost(PostDTO postdto,int userId,int categoryId) {
		Post post = mappers.modelMapper().map(postdto, Post.class);
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		post.setUser(user);
		post.setCategory(category);
		post.setPostContent(postdto.getPostContent());
		post.setPostImage("default.png");
		post.setPostTitle(postdto.getPostTitle());
		post.setAddedDate(new Date());
		postRepo.save(post);
		return mappers.modelMapper().map(post, PostDTO.class);
	}

	@Override
	public void deletePost(int postId) {
		if(postRepo.existsById(postId)) {
			postRepo.deleteById(postId);
		}else {
			throw new ResourceNotFoundException("Post", "id", postId);
		}

	}

	@Override
	public PostDTO updatePost(PostDTO postdto, int postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setPostContent(postdto.getPostContent());
		post.setPostImage(postdto.getPostImage());
		post.setPostTitle(postdto.getPostTitle());
		postRepo.save(post);
		return mappers.modelMapper().map(post, PostDTO.class);
	}

	@Override
	public PostResponse searchPost(String keyword,int pageSize,int pageNumber,String sortBy) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = postRepo.searchByTitle("%"+keyword+"%",page);
		List<PostDTO> postDtos = pagePost.stream().map((post) -> mappers.modelMapper().map(post, PostDTO.class)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		return postResponse;
	}

}
