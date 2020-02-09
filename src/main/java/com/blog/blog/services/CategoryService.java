package com.blog.blog.services;


import com.blog.blog.models.Article;
import com.blog.blog.models.Category;
import com.blog.blog.repositories.ArticleRepository;
import com.blog.blog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
