package com.osman.blog.services;

import com.osman.blog.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto comment, Integer postId);
    CommentDto updateComment(CommentDto comment, Integer postId);
    void deleteComment(Integer commentId);

    CommentDto getComment(Integer id);

}
