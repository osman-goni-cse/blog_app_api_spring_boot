package com.osman.blog.repositories;

import com.osman.blog.entities.Category;
import com.osman.blog.entities.Post;
import com.osman.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
//    Page<Post> findAllByPagination(Pageable pageable);
}
