package com.blog.blog.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    private String label;

    @JsonIgnore
    @OneToMany
    List<Article> articles;

}
