package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Payloads.JWTAuthRequest;
import com.example.blog.blogappapis.Payloads.JWTAuthResponse;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Security.JwtTokenHelper;
import com.example.blog.blogappapis.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name="Authentication")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @PostMapping("/login")
    @Operation(
            description = "To login in application ",
            summary="To login in blog application using JWT token",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized/Invalid token",
                            responseCode = "403"
                    )
            }
    )
    public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request) throws Exception {
        log.info("JWTAuthRequest: "+request.getUsername()+" "+request.getPassword());
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
        log.info("UserDetails in authConroller: "+userDetails);
        String token = this.jwtTokenHelper.generateToken(userDetails);
        log.info("Token generated in authConrroller: "+token);
        JWTAuthResponse response=new JWTAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);


    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            log.info("Invalid details !!");
            throw new Exception("Invalid Username or password");
        }
    }

    //register new user API

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registeredUser=this.userService.registerNewUser(userDto);

        return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);



    }
}

