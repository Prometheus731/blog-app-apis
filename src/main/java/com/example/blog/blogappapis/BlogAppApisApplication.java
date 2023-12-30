package com.example.blog.blogappapis;

import com.example.blog.blogappapis.Config.AppConstants;
import com.example.blog.blogappapis.Config.PasswordEncoderConfig;
import com.example.blog.blogappapis.Entities.Role;
import com.example.blog.blogappapis.Repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoderConfig passwordEncoderConfig;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean  //used to create a bean object so that this object can be autowired to other class for depedency injection.
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Encoded password: "+this.passwordEncoderConfig.passwordEncoder().encode("12345"));

		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setRoleName("ADMIN_USER");

			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setRoleName("NORMAL_USER");

			List<Role> roles = List.of(role, role1);
			List<Role> resultRole=this.roleRepo.saveAll(roles);

			resultRole.forEach(r-> System.out.println(r.getRoleName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
