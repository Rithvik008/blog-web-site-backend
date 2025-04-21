package com.thinkingrodent.blog.blog_app_apis.services.impl;

import com.thinkingrodent.blog.blog_app_apis.entities.Comment;
import com.thinkingrodent.blog.blog_app_apis.entities.Post;
import com.thinkingrodent.blog.blog_app_apis.entities.User;
import com.thinkingrodent.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.thinkingrodent.blog.blog_app_apis.payloads.CommentDto;
import com.thinkingrodent.blog.blog_app_apis.repositories.CommentRepo;
import com.thinkingrodent.blog.blog_app_apis.repositories.PostRepo;
import com.thinkingrodent.blog.blog_app_apis.repositories.UserRepo;
import com.thinkingrodent.blog.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
        Post post=this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        User user=this.userRepo.findById(userId)
                        .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        comment.setUser(user);
        Comment createdComment=this.commentRepo.save(comment);
        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepo.delete(comment);

    }

}
