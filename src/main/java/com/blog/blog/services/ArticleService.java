package com.blog.blog.services;

import com.blog.blog.models.Article;
import com.blog.blog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepo;

    public Article save(Article article) {
        return articleRepo.save(article);
    }

    public Optional<Article> findById(Integer id) {
        return articleRepo.findById(id);
    }

    public void deleteById(Integer id) {
        articleRepo.deleteById(id);
    }

    public List<Article> findAll() {
        return articleRepo.findAll();
    }

    public Page<Article> findAllByPages(int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, sort);
        return articleRepo.findAll(pageRequest);
    }

    public List<Article> findByTitleLike(String s) {
        return articleRepo.findByTitleLike(s);
    }

    public List<Article> findByAuthorId(Integer user_id) {
        return articleRepo.findByAuthorId(user_id);
    }

    public List<Article> findByCategoriesLabel(String category_label) {
        return articleRepo.findByCategoriesLabel(category_label);
    }

}
