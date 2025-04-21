package com.thinkingrodent.blog.blog_app_apis.controllers;

import com.thinkingrodent.blog.blog_app_apis.entities.Comment;
import com.thinkingrodent.blog.blog_app_apis.payloads.ApiResponse;
import com.thinkingrodent.blog.blog_app_apis.payloads.CommentDto;
import com.thinkingrodent.blog.blog_app_apis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,
                                                    @PathVariable Integer userId){
        CommentDto createdComment=this.commentService.createComment(commentDto,postId,userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}/user/{userId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted!",true),HttpStatus.OK);

    }
}
