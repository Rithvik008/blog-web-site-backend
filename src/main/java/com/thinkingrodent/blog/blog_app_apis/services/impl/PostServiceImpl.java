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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setUser(post.getUser());
        post.setCategory(post.getCategory());
        Post updatedPost = this.postRepo.save(post);

        PostDto updatedPostDto = this.modelMapper.map(updatedPost, PostDto.class);
        updatedPostDto.setUserDto(this.modelMapper.map(updatedPost.getUser(), UserDto.class));
        updatedPostDto.setCategoryDto(this.modelMapper.map(updatedPost.getCategory(), CategoryDto.class));

        return updatedPostDto;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
//        int pageSize=5;
//        int pageNumber=1;

        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Post> pagePost=this.postRepo.findAll(pageable);
//        imppp
        List<Post> posts=pagePost.getContent();
        List<PostDto> postDtos = posts.stream()

                .map(post -> {
                    PostDto dto = this.modelMapper.map(post, PostDto.class);
                    dto.setCategoryDto(this.modelMapper.map(post.getCategory(), CategoryDto.class));
                    dto.setUserDto(this.modelMapper.map(post.getUser(), UserDto.class));
                    return dto;
                })
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        PostDto postDto=this.modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        List<Post> posts = this.postRepo.findByCategory(category);

        List<PostDto> postDtos = posts.stream()
                .map(post -> {
                    PostDto dto = this.modelMapper.map(post, PostDto.class);
                    dto.setCategoryDto(this.modelMapper.map(post.getCategory(), CategoryDto.class));
                    dto.setUserDto(this.modelMapper.map(post.getUser(), UserDto.class));
                    return dto;
                })
                .collect(Collectors.toList());

        return postDtos;
    }


    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream()
                .map(post -> {
                    PostDto dto = this.modelMapper.map(post, PostDto.class);
                    dto.setCategoryDto(this.modelMapper.map(post.getCategory(), CategoryDto.class));
                    dto.setUserDto(this.modelMapper.map(post.getUser(), UserDto.class));
                    return dto;
                })
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
