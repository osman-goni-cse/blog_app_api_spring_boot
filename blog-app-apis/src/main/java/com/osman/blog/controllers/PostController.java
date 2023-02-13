package com.osman.blog.controllers;

import com.osman.blog.entities.Post;
import com.osman.blog.payloads.PostDto;
import com.osman.blog.services.PostService;
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

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = this.postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
    }
}
