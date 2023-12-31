package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Config.AppConstants;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Payloads.ApiResponse;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Payloads.PostResponse;
import com.example.blog.blogappapis.Services.FileService;
import com.example.blog.blogappapis.Services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.kerberos.KerberosTicket;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name="Post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${Project.Image}")
    private String path;

    //create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId){
        PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                       @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                       @PathVariable Integer userId){
        PostResponse postResponse=this.postService.getPostsByUser(pageNumber,pageSize,userId);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                            @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                            @PathVariable Integer categoryId){
        PostResponse postResponse=this.postService.getPostsByCategory(pageNumber,pageSize,categoryId);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                    @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                    @RequestParam(value="sort",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                    @RequestParam(value="sortDirection",defaultValue = AppConstants.SORT_DIR,required = false) String sortDirection){
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    //get post by id

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto post=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted !!",true);
    }

    //update Post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatePost=this.postService.UpdatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    //searching
    @GetMapping("/posts/search/{keyWords}")
    public ResponseEntity<List<PostDto>> searchPostbyTitle(@PathVariable("keyWords") String keyWord) {
        List<PostDto> result = this.postService.searchPosts(keyWord);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

    //post image upload
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                   @PathVariable Integer postId) throws IOException {
        String fileName=this.fileService.uploadImage(path,image);
        PostDto postDto=this.postService.getPostById(postId);
        postDto.setImagename(fileName);
        PostDto updatePost=this.postService.UpdatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }

    //to serve image file
    @GetMapping(value = "/profiles/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String ImageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path,ImageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
