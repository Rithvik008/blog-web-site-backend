package com.thinkingrodent.blog.blog_app_apis.services;

import com.thinkingrodent.blog.blog_app_apis.entities.Post;
import com.thinkingrodent.blog.blog_app_apis.payloads.PostDto;
import com.thinkingrodent.blog.blog_app_apis.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);
}
