package com.osman.blog.repositories;

import com.osman.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
