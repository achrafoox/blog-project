package com.blog.blog.controllers;

import com.blog.blog.exceptions.ArticlenotFoundException;
import com.blog.blog.exceptions.UsernotFoundException;
import com.blog.blog.models.Article;
import com.blog.blog.repositories.ArticleRepository;
import com.blog.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepo;


    // Add an article :
    @PostMapping(value = "/articles/")
    public ResponseEntity<Object> addArticle(@RequestBody Article article){
        Article savedEmployee = articleRepo.save(article);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // update an article :
    @PutMapping("/articles/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable Integer id, @RequestBody Article article){
        Optional<Article> newarticle = articleRepo.findById(id);
        if (!newarticle.isPresent())
            return ResponseEntity.notFound().build();
        article.setId(id);
        articleRepo.save(article);
        return ResponseEntity.noContent().build();
    }

    // delete an article :
    @DeleteMapping(value = "/articles/{id}")
    public void deleteArticle(@PathVariable Integer id){
        articleRepo.deleteById(id);
    }


    // get all articles :
    @GetMapping(value = "/articles/")
    public List<Article> getArticles(){
        return articleRepo.findAll();
    }


    // get articles by id :
    @GetMapping(value="/articles/{id}")
    public Optional<Article> getArticleById(@PathVariable Integer id) throws ArticlenotFoundException {
        Optional<Article> article = articleRepo.findById(id) ;
        if(article==null) throw new ArticlenotFoundException("L'article  avec l'id = " + id + " est INTROUVABLE !");
        return article;
    }


}
