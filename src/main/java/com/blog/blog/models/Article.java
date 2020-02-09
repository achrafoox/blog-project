package com.blog.blog.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @Column(length=1000)
    private String body;

    private String description;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @ManyToMany
    private List<Category> categories;

}