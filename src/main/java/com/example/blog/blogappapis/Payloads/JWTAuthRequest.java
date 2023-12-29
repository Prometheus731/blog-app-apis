package com.example.blog.blogappapis.Payloads;

import lombok.Data;

@Data
public class JWTAuthRequest {

    private String username;
    private String password;
}
