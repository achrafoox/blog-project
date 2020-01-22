package com.blog.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
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

    private String name;
    private String role;
    private String email;


    @OneToMany
    @JsonIgnore
    private List<Article> articles;


}
