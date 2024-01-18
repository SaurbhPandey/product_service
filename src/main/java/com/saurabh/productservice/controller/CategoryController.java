package com.saurabh.productservice.controller;

import com.saurabh.productservice.dtos.CategoryDto;
import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.NotFoundException;
import com.saurabh.productservice.services.categoryservice.CategoryService;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Category")
public class CategoryController {
  private CategoryService categoryService;
  public CategoryController(CategoryService categoryService){
      this.categoryService = categoryService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<GenericResponseHandler<CategoryDto>> getCategoryById(@PathVariable ("id") UUID id ) throws NotFoundException {
      return new ResponseEntity<GenericResponseHandler<CategoryDto>>(categoryService.getCategoryById(id) , HttpStatus.OK);
  }

  @GetMapping("/categoryname/{name}")
    public ResponseEntity<GenericResponseHandler<CategoryDto>> getCategoryByName(@PathVariable ("name") String name) throws NotFoundException{
      return new ResponseEntity<GenericResponseHandler<CategoryDto>>(categoryService.getCategoryByName(name) , HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<GenericResponseHandler<List<String>>> getAllCategory(){
    return new ResponseEntity<GenericResponseHandler<List<String>>>(categoryService.getAllCategory() , HttpStatus.OK);
  }
}
