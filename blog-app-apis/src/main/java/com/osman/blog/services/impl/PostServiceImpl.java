package com.osman.blog.services.impl;

import com.osman.blog.entities.Category;
import com.osman.blog.entities.Post;
import com.osman.blog.entities.User;
import com.osman.blog.exceptions.ResourceNotFoundException;
import com.osman.blog.payloads.PostDto;
import com.osman.blog.repositories.CategoryRepo;
import com.osman.blog.repositories.PostRepo;
import com.osman.blog.repositories.UserRepo;
import com.osman.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User Does Not Exist", "UserID: ", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new
                ResourceNotFoundException("Category Does Not Exist", "Category ID: ", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate( new Date() );
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer categoryId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(
                "Post does not exist", "PostID: ", postId
        ));
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "User does not exist.", "UserID: ", userId
        ));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(
                "Category Does not exist", "Category ID: ", categoryId
        ));

        post.setUser(user);
        post.setCategory(category);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(new Date());

        Post updatedPost = this.postRepo.save(post);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new
                ResourceNotFoundException("Post Does Not exist", " Post ID: ", postId));


        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(
                "Post Does not exist", "PostID: ", postId
        ));

        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category Not Found With ", "CategoryID: ", categoryId));

        List<Post> posts = this.postRepo.findByCategory(category);

        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(
                post, PostDto.class
        )).collect(Collectors.toList());


        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User Does Not Exist.", "UserID: ", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map(post ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
