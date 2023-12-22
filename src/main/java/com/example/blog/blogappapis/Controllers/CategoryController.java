package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Payloads.ApiResponse;
import com.example.blog.blogappapis.Payloads.CategoryDto;
import com.example.blog.blogappapis.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createcategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
        CategoryDto categoryDto1=categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/catId")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>
                (new ApiResponse("category is deleted successfully",false),HttpStatus.OK);
    }

    //get
    @GetMapping("/catId")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId){
        CategoryDto categoryDto=categoryService.getCategoryById(catId);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
    //getAll
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtoList=categoryService.getAllCategories();
        //return new ResponseEntity<List<CategoryDto>>(categoryDtoList,HttpStatus.OK);
        return ResponseEntity.ok(categoryDtoList);
    }
}
