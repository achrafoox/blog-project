package com.blog.blog.controllers;

import com.blog.blog.exceptions.CategorynotFoundException;
import com.blog.blog.models.Category;
import com.blog.blog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class CategoryController {


    @Autowired
    private CategoryRepository categoryRep;


    // Add an category :
    @PostMapping(value = "/categories/")
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        Category savedEmployee = categoryRep.save(category);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // update an category :
    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Integer id, @RequestBody Category category){
        Optional<Category> newcategory = categoryRep.findById(id);
        if (!newcategory.isPresent())
            return ResponseEntity.notFound().build();
        category.setId(id);
        categoryRep.save(category);
        return ResponseEntity.noContent().build();
    }

    // delete an category :
    @DeleteMapping(value = "/categories/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryRep.deleteById(id);
    }


    // get all categories :
    @GetMapping(value = "/categories/")
    public List<Category> getCategories(){
        return categoryRep.findAll();
    }


    // get categories by id :
    @GetMapping(value="/categories/{id}")
    public Optional<Category> getCategoryById(@PathVariable Integer id) throws CategorynotFoundException {
        Optional<Category> category = categoryRep.findById(id) ;
        if(category==null) throw new CategorynotFoundException("la categorie  avec l'id = " + id + " est INTROUVABLE !");
        return category;
    }


}
