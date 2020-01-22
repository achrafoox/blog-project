package com.blog.blog.models;

import lombok.*;
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
    private String body;
    private String description;

    @OneToOne
    @JoinColumn(name="author_id")
    private User author;

    @OneToMany
    private List<Category> categories;

}
