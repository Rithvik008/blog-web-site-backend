package com.thinkingrodent.blog.blog_app_apis.controllers;
import com.thinkingrodent.blog.blog_app_apis.payloads.ApiResponse;
import com.thinkingrodent.blog.blog_app_apis.payloads.PostDto;
import com.thinkingrodent.blog.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

//    create user
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer categoryId,@PathVariable Integer userId){
        PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

//    get single post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getpostById(@PathVariable Integer postId){
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

//    get posts by user id
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> posts=this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

//    get posts by category id
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

//     get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize",defaultValue = "2",required = false) Integer pageSize){
        List<PostDto> postDtos=this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

//    delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPost=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }
}
