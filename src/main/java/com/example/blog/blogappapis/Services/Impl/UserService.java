package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Exceptions.ResourceNotFound;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements com.example.blog.blogappapis.Services.UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.UserToDto(savedUser);
    }

    @Override
    public UserDto updateUSer(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.UserToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User","id",userId));
        return this.UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepo.findAll();
        return users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User","Id",userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
//        User user1=new User();
//        user1.setId(userDto.getId());
//        user1.setName(userDto.getName());
//        user1.setEmail(userDto.getEmail());
//        user1.setPassword(userDto.getPassword());
//        user1.setAbout(userDto.getPassword());
        User user1=this.modelMapper.map(userDto,User.class);

        return user1;
    }

    public UserDto UserToDto(User user){
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
