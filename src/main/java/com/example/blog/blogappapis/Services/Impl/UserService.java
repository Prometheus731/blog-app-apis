package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService implements com.example.blog.blogappapis.Services.UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.UserToDto(savedUser);
    }

    @Override
    public UserDto updateUSer(UserDto user, Integer userId) {
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    public User dtoToUser(UserDto userDto){
        User user1=new User();
        user1.setId(userDto.getId());
        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(userDto.getPassword());
        user1.setAbout(userDto.getPassword());

        return user1;
    }

    public UserDto UserToDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
