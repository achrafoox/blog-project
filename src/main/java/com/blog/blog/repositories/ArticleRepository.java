package com.blog.blog.repositories;


import com.blog.blog.models.Article;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public List<Article> findByTitleLike(String title);

    public List<Article> findByAuthorId(Integer user_id);

    public List<Article> findByCategoriesLabel(String category_label);

}
