package com.osman.blog.services;

import com.osman.blog.entities.Post;
import com.osman.blog.payloads.PostDto;
import com.osman.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    PostDto getPostById(Integer postId);
    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

//    List<PostDto> searchPostsByContent(String keyword);
}
