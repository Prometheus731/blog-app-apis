package com.example.blog.blogappapis.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private int id;
    private String title;
    private String content;
    private String imagename;
    private Date addeddate;
    private CategoryDto category;
    private UserDto user;

}
