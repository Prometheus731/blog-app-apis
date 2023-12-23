package com.example.blog.blogappapis.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users") //to change the name of the user table to users in mysql database
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;

   @Column(name="user_name", nullable = false, length = 100)
   private String name;
   private String email;
   private String password;
   private String about;

   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   private List<Post> postList=new ArrayList<>();

}
