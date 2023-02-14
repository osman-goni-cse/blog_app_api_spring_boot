package com.osman.blog.services;

import com.osman.blog.entities.Post;
import com.osman.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer categoryId);
    PostDto getPostById(Integer postId);
    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);
}
