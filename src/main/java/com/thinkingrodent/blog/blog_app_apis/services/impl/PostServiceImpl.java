package com.thinkingrodent.blog.blog_app_apis.services.impl;

import com.thinkingrodent.blog.blog_app_apis.entities.Category;
import com.thinkingrodent.blog.blog_app_apis.entities.Post;
import com.thinkingrodent.blog.blog_app_apis.entities.User;
import com.thinkingrodent.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.thinkingrodent.blog.blog_app_apis.payloads.CategoryDto;
import com.thinkingrodent.blog.blog_app_apis.payloads.PostDto;
import com.thinkingrodent.blog.blog_app_apis.payloads.UserDto;
import com.thinkingrodent.blog.blog_app_apis.repositories.CategoryRepo;
import com.thinkingrodent.blog.blog_app_apis.repositories.PostRepo;
import com.thinkingrodent.blog.blog_app_apis.repositories.UserRepo;
import com.thinkingrodent.blog.blog_app_apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));

        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost =this.postRepo.save(post);
//        return this.modelMapper.map(newPost,PostDto.class);
        PostDto responseDto = this.modelMapper.map(newPost, PostDto.class);
        responseDto.setUserDto(this.modelMapper.map(newPost.getUser(), UserDto.class));
        responseDto.setCategoryDto(this.modelMapper.map(newPost.getCategory(), CategoryDto.class));
        return responseDto;

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
//        Post post=
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts=this.postRepo.findAll();
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
