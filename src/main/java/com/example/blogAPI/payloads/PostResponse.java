package com.example.blogAPI.payloads;

import java.util.List;

public class PostResponse {
	private List<PostDTO> content;
	private int pageSize;
	private int PageNumber;
	private long totalElement;
	private int totalPages;
	private boolean lastPage;
	
	
	public long getTotalElement() {
		return totalElement;
	}
	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}
	public List<PostDTO> getContent() {
		return content;
	}
	public void setContent(List<PostDTO> content) {
		this.content = content;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return PageNumber;
	}
	public void setPageNumber(int pageNumber) {
		PageNumber = pageNumber;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public PostResponse(List<PostDTO> content, int pageSize, int pageNumber, long totalElement, int totalPages,
			boolean lastPage) {
		super();
		this.content = content;
		this.pageSize = pageSize;
		PageNumber = pageNumber;
		this.totalElement = totalElement;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}
	public PostResponse() {
		super();
	}
	
}
