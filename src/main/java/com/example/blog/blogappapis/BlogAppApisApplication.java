package com.example.blog.blogappapis;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean  //used to create a bean object so that this object can be autowired to other class for depedency injection.
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
