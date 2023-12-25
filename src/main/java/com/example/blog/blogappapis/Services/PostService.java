package com.example.blog.blogappapis.Services;

import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto UpdatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts

    PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy,String sortDirection);

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts By category
    PostResponse getPostsByCategory(Integer pageNo,Integer pageSize,Integer categoryId);

    //get all Posts by user
    PostResponse getPostsByUser(Integer pageNo,Integer pageSize,Integer userId);

    //search posts
    List<Post> searchPosts(String keyWord);

}
