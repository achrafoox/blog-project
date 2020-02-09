package com.blog.blog.controllers;

import com.blog.blog.exceptions.CategorynotFoundException;
import com.blog.blog.models.Category;
import com.blog.blog.repositories.CategoryRepository;
import com.blog.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    // Add a category :
    @PostMapping(value = "/categories/")
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        Category savedEmployee = categoryService.save(category);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    // update an category :
    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Integer id, @RequestBody Category category){
        Optional<Category> newcategory = categoryService.findById(id);
        if (!newcategory.isPresent())
            return ResponseEntity.notFound().build();
        category.setId(id);
        categoryService.save(category);
        return ResponseEntity.noContent().build();
    }


    // delete an category :
    @DeleteMapping(value = "/categories/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryService.deleteById(id);
    }


    // get all categories :
    @GetMapping(value = "/categories/")
    public List<Category> getCategories(){
        return categoryService.findAll();
    }


    // get categories by id :
    @GetMapping(value="/categories/{id}")
    public Optional<Category> getCategoryById(@PathVariable Integer id) throws CategorynotFoundException {
        Optional<Category> category = categoryService.findById(id) ;
        if(category==null) throw new CategorynotFoundException("la categorie  avec l'id = " + id + " est INTROUVABLE !");
        return category;
    }


}
