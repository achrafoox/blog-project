package com.blog.blog.controllers;

import com.blog.blog.exceptions.ArticlenotFoundException;
import com.blog.blog.exceptions.UsernotFoundException;
import com.blog.blog.models.Article;
import com.blog.blog.repositories.ArticleRepository;
import com.blog.blog.repositories.UserRepository;
import com.blog.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ArticleController {


    @Autowired
    private ArticleService articleService;


    // Add an article :
    @PostMapping(value = "/articles/")
    public ResponseEntity<Object> addArticle(@RequestBody Article article){
        Article savedEmployee = articleService.save(article);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // update an article :
    @PutMapping("/articles/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable Integer id, @RequestBody Article article){
        Optional<Article> newarticle = articleService.findById(id);
        if (!newarticle.isPresent())
            return ResponseEntity.notFound().build();
        article.setId(id);
        articleService.save(article);
        return ResponseEntity.noContent().build();
    }

    // delete an article :
    @DeleteMapping(value = "/articles/{id}")
    public void deleteArticle(@PathVariable Integer id){
        articleService.deleteById(id);
    }


    // get all articles :
    @GetMapping(value = "/articles/")
    public List<Article> getArticles(){
        return articleService.findAll();
    }


    // get all articles page by page :
    @GetMapping(value = "/pagedarticles")
    public Page<Article> getArticles(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size, @RequestParam Optional<String> sort){
        return articleService.findAllByPages(page.orElse(0), size.orElse(4), sort.orElse("id"));
    }


    // get articles by id :
    @GetMapping(value="/articles/{id}")
    public Optional<Article> getArticleById(@PathVariable Integer id) throws ArticlenotFoundException {
        Optional<Article> article = articleService.findById(id) ;
        if(article==null) throw new ArticlenotFoundException("L'article  avec l'id = " + id + " est INTROUVABLE !");
        return article;
    }


    // get articles containing 'query' in the title :
    @GetMapping("/articles/q={query}")
    public List<Article> getArticleByTitle(@PathVariable String query){
        List<Article> articles = articleService.findByTitleLike("%"+query+"%");
        return articles;
    }

    // get articles of an author :
    @GetMapping("/articles/user/{user_id}")
    public List<Article> getArticlesByAuthor(@PathVariable Integer user_id){
        List<Article> articles = articleService.findByAuthorId(user_id);
        return articles ;
    }


    // get articles by a category :
    @GetMapping("/articles/category/{category_label}")
    public List<Article> getArticlesByCategory(@PathVariable String category_label){
        List<Article> articles = articleService.findByCategoriesLabel(category_label);
        return articles ;
    }

}
