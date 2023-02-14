package com.osman.blog.controllers;

import com.osman.blog.config.AppConstants;
import com.osman.blog.entities.Post;
import com.osman.blog.payloads.PostDto;
import com.osman.blog.payloads.PostResponse;
import com.osman.blog.services.FileService;
import com.osman.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam (value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam (value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {

        PostResponse posts = this.postService.getAllPost(pageNumber, pageSize, sortBy);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = this.postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Integer postId) {
        PostDto updatePostDto = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePostDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Integer postId) {

        this.postService.deletePost(postId);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    Post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

//    method to serve files
    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,
                              HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        if (resource != null) {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(resource, response.getOutputStream());
        }
        else {
            System.out.println("Image Not Found.");
        }
    }
}
