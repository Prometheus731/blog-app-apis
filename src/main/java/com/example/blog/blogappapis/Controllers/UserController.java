package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Payloads.ApiResponse;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Services.Impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUSerDto= this.userService.createUser(userDto);
        return new ResponseEntity<>(createUSerDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userid") Integer uid){  //if we have given name in pathvariable as same as put mapping name then we can change the name for parameter to uid or anything
        UserDto updatedUser=this.userService.updateUSer(userDto,uid);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userid") Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userid}")
    public ResponseEntity<UserDto> getsingleUser(@PathVariable Integer userid){
        return ResponseEntity.ok(this.userService.getUserById(userid));

    }
}
