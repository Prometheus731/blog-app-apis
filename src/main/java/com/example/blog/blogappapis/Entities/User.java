package com.example.blog.blogappapis.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users") //to change the name of the user table to users in mysql database
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="user_name", nullable = false, length = 100)
    private String name;
    private String email;
    private String password;
    private String about;

}
