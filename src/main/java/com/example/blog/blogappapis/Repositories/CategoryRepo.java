package com.example.blog.blogappapis.Repositories;

import com.example.blog.blogappapis.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
