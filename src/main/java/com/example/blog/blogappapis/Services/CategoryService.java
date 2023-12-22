package com.example.blog.blogappapis.Services;

import com.example.blog.blogappapis.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);


    //delete
    void deleteCategory(Integer categoryId);

    //get
    CategoryDto getCategoryById(Integer categoryId);

    //get All
    List<CategoryDto> getAllCategories();
}
