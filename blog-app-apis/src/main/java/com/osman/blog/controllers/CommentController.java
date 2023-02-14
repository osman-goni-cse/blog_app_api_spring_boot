package com.osman.blog.controllers;

import com.osman.blog.entities.Comment;
import com.osman.blog.payloads.CommentDto;
import com.osman.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createPost(@RequestBody CommentDto commentDto,
                                                 @PathVariable Integer postId){
        CommentDto newCommentDto = this.commentService.createComment(commentDto, postId);

        return new ResponseEntity<CommentDto>(newCommentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
    }
}
