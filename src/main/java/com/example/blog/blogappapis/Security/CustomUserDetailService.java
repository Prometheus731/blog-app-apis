package com.example.blog.blogappapis.Security;

import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //loading user from database by username
        log.info("Accessing loadUserByUserName");
        User user=this.userRepo.findByEmail(userName).orElseThrow(()->
                new ResourceNotFoundException("User","email",userName));
        return user;
    }
}
