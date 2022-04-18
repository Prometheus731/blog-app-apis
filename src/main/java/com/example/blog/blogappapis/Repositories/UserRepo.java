package com.example.blog.blogappapis.Repositories;

import com.example.blog.blogappapis.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
