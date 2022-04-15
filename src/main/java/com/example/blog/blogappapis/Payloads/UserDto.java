package com.example.blog.blogappapis.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Data transfer object (DTO) (we will not be accessing the entity class directly, we will be using DTO class for this.)
@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
