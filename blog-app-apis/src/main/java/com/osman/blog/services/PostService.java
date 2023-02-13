package com.osman.blog.services;

import com.osman.blog.entities.Post;
import com.osman.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer postId);
    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    List<Post> getPostByCategory(Integer categoryId);

    List<Post> getPostsByUser(Integer userId);

    List<Post> searchPosts(String keyword);
}
