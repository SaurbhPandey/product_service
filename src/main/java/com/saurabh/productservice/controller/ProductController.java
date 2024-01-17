package com.saurabh.productservice.controller;

import com.saurabh.productservice.dtos.GenericProductDto;
import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.IncompleteDetailsException;
import com.saurabh.productservice.exceptions.NotFoundException;
import com.saurabh.productservice.services.productservice.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Products")
public class ProductController {
     public ProductService productService;
     public ProductController(ProductService productService){
         this.productService = productService;
     }

     @GetMapping("/{id}")
     public ResponseEntity<GenericResponseHandler<GenericProductDto>> getProductById(@PathVariable("id") UUID id) throws NotFoundException {
          ResponseEntity<GenericResponseHandler<GenericProductDto>> product = new ResponseEntity<>(productService.getProductById(id) , HttpStatus.OK);
          return product;
     }

     @PostMapping
     public ResponseEntity<GenericResponseHandler<GenericProductDto>> createProduct(@RequestBody GenericProductDto genericProductDto) throws IncompleteDetailsException {
          return new ResponseEntity<GenericResponseHandler<GenericProductDto>>(productService.createProduct(genericProductDto) , HttpStatus.CREATED);
     }

     @GetMapping
     public ResponseEntity<GenericResponseHandler<List<GenericProductDto>>> getAllProduct(){
          GenericResponseHandler<List<GenericProductDto>>  genericResponseHandler = productService.getAllProduct();
          return new ResponseEntity<GenericResponseHandler<List<GenericProductDto>>>(genericResponseHandler , HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<GenericResponseHandler<GenericProductDto>> deleteProductById(@PathVariable ("id") UUID id) throws NotFoundException{
          return new ResponseEntity<GenericResponseHandler<GenericProductDto>>(productService.deleteroductById(id) , HttpStatus.NO_CONTENT);
     }

     @PutMapping("/updateproduct")
     public ResponseEntity<GenericResponseHandler<GenericProductDto>> updateProductById(@RequestBody GenericProductDto genericProductDto) throws NotFoundException{
          return new ResponseEntity<GenericResponseHandler<GenericProductDto>>(productService.updateProduct(genericProductDto) , HttpStatus.OK);
     }
}
