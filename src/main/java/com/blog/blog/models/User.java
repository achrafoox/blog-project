package com.blog.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    @Column(unique = true)
    @Length(max = 199)
    private String email;

    @Length(min = 4)
    @JsonIgnore
    private String password;

    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES",
            inverseJoinColumns = {@JoinColumn(name = "role_id",  referencedColumnName = "id"),},
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

}
