package com.example.blog.blogappapis.Repositories;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    Page<Post> findByUser(Pageable pageable,User user);
    Page<Post> findByCategory(Pageable pageable, Category category);

}
