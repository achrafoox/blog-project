package com.blog.blog.security;

import com.blog.blog.models.Article;
import com.blog.blog.models.Role;
import com.blog.blog.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MyUserDetails implements UserDetails {
    private String email;
    private Integer id;
    private String name;
    private String password;
    private boolean active;
    private List<Role> roles;
    // private List<GrantedAuthority> authorities;
    // private Collection<? extends GrantedAuthority> authorities;


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public MyUserDetails(String email){
        this.email = email ;
    }

    public MyUserDetails(){

    }

    public MyUserDetails(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = getPasswordEncoder().encode(user.getPassword());
        this.roles = user.getRoles();
        this.active = user.isActive();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach( role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            authorities.add(authority);
        });

        return authorities;

      //  return AuthorityUtils.NO_AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
