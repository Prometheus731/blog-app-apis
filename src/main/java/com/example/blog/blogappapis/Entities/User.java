package com.example.blog.blogappapis.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "users") //to change the name of the user table to users in mysql database
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;

   @Column(name="user_name", nullable = false, length = 100)
   private String name;
   private String email;
   private String password;
   private String about;

   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   private List<Post> postList=new ArrayList<>();

   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinTable(name = "user_role",
           joinColumns = @JoinColumn(name = "user",referencedColumnName = "id"),
   inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))
   private Set<Role> roleset=new HashSet<>();

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      List<SimpleGrantedAuthority> authorities=this.roleset.stream().map((role)->
              new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
      log.info("Authorities created using roles in user entity class :"+authorities);
      return authorities;
   }

   @Override
   public String getUsername() {
      return this.email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
