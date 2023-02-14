package com.osman.blog.services.impl;

import com.osman.blog.entities.Comment;
import com.osman.blog.entities.Post;
import com.osman.blog.exceptions.ResourceNotFoundException;
import com.osman.blog.payloads.CommentDto;
import com.osman.blog.repositories.CommentRepo;
import com.osman.blog.repositories.PostRepo;
import com.osman.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post Does not exist. ", "PostID: ", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setContent(commentDto.getContent());
        comment.setPost(post);

        Comment newComment = this.commentRepo.save(comment);

        return this.modelMapper.map(newComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto comment, Integer postId) {
        return null;
    }

    @Override
    public void deleteComment(Integer id) {
        Comment cmnt = this.commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Comment Does Not Exist.", "CommentID: ", id
        ));

        this.commentRepo.delete(cmnt);
    }

    @Override
    public CommentDto getComment(Integer id) {
        return null;
    }
}
