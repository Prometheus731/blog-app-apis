package com.example.blog.blogappapis.Payloads;

import com.example.blog.blogappapis.Entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


//Data transfer object (DTO) (we will not be accessing the entity class directly, we will be using DTO class for this.)
@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private int id;

    @NotEmpty
    @Size(min = 4,message = "User name must be 4 character")
    private String name;

    @Email(message = "Your email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be min 3 characters and max 10 characters")
    
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roleset=new HashSet<>();
}
