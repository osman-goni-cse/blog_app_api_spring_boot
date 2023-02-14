package com.osman.blog.repositories;

import com.osman.blog.entities.Category;
import com.osman.blog.entities.Post;
import com.osman.blog.entities.User;
import com.osman.blog.payloads.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
//    @Query("SELECT p FROM Post p WHERE p.content LIKE :key")
//    List<Post> searchByContent(@Param("key") String content);
}
