package com.saurabh.productservice.services.productservice;

import com.saurabh.productservice.dtos.GenericProductDto;
import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.IncompleteDetailsException;
import com.saurabh.productservice.exceptions.NotFoundException;
import com.saurabh.productservice.models.Category;
import com.saurabh.productservice.models.Product;
import com.saurabh.productservice.repository.CategoryRepository;
import com.saurabh.productservice.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private EntityManager entityManager;
    public ProductServiceImpl(ProductRepository productRepository , CategoryRepository categoryRepository, EntityManager entityManager){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    private GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getUuid());
        genericProductDto.setCategory(product.getCategory());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        return genericProductDto;
    }

    private Product convertGenericProductDtoToProductDto(GenericProductDto productDto){
        Product product = new Product();
        product.setImage(productDto.getImage());
        product.setTitle(productDto.getTitle());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return product;
    }
    @Override
    public GenericResponseHandler<GenericProductDto> getProductById(UUID id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
          throw new NotFoundException("Product with id "  + id + " is not present , please check the product id");
        }
        Product product = productOptional.get();
        GenericProductDto genericProductDto = convertProductToGenericProductDto(product);
        GenericResponseHandler<GenericProductDto> genericResponseHandler = new GenericResponseHandler<>();
        genericResponseHandler.setData(genericProductDto);
        genericResponseHandler.setMessage("Product fetched successfully");
        genericResponseHandler.setHttpStatus(HttpStatus.OK);
        return genericResponseHandler;
    }

    @Override
    public GenericResponseHandler<GenericProductDto> createProduct(GenericProductDto genericProductDto) throws IncompleteDetailsException {
        if(genericProductDto.getTitle() == null || genericProductDto.getPrice() == null || genericProductDto.getCategory() == null ){
            throw new IncompleteDetailsException("Failed to create product , Please Enter all the required details !");
        }
       Product product = productRepository.save(convertGenericProductDtoToProductDto(genericProductDto));
       GenericProductDto productDto = convertProductToGenericProductDto(product);

       GenericResponseHandler<GenericProductDto> genericResponseHandler = new GenericResponseHandler<>();
       genericResponseHandler.setData(productDto);
       genericResponseHandler.setHttpStatus(HttpStatus.CREATED);
       genericResponseHandler.setMessage("Product is created successfully");
       return genericResponseHandler;
    }

    @Override
    public GenericResponseHandler<List<GenericProductDto>> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<GenericProductDto> genericProductList = new ArrayList<>();
        productList.forEach(product -> {
            GenericProductDto genericProductDto = convertProductToGenericProductDto(product);
            genericProductList.add((genericProductDto));
        });
        GenericResponseHandler<List<GenericProductDto>> genericResponseHandler = new GenericResponseHandler<>();
        if(genericProductList.size() > 0){
            genericResponseHandler.setMessage("All product feteched successfully !!");
        }
        else{
            genericResponseHandler.setMessage("No product is available");
        }
        genericResponseHandler.setData(genericProductList);
        genericResponseHandler.setHttpStatus(HttpStatus.OK);
        return genericResponseHandler;
    }

    @Override
    public GenericResponseHandler<GenericProductDto> deleteroductById(UUID id) throws NotFoundException {
       Optional<Product> productOptional = productRepository.findById(id);
       if(productOptional.isEmpty()){
           throw new NotFoundException("Product with id " + id + " is not exist , Please enter the correct Id !!");
       }
       productRepository.deleteById(id);
       GenericResponseHandler<GenericProductDto> genericResponseHandler = new GenericResponseHandler<>();
       genericResponseHandler.setHttpStatus(HttpStatus.NO_CONTENT);
       genericResponseHandler.setMessage("Deleted Product Succesfully");
       genericResponseHandler.setData(new GenericProductDto());
       return genericResponseHandler;
    }

//    @Override
//    @Transactional
//    public GenericResponseHandler<GenericProductDto> updateProduct(GenericProductDto genericProductDto) throws NotFoundException, IncompleteDetailsException {
//        if(genericProductDto.getId() == null){
//            throw new IncompleteDetailsException("Please provide the product id which you want to update");
//        }
//        Optional<Product> productOptional = productRepository.findById(genericProductDto.getId());
//        if(productOptional.isEmpty()){
//            throw new NotFoundException("No product is available with id " + genericProductDto.getId());
//        }
//        Product product = convertGenericProductDtoToProductDto(genericProductDto);
//        productRepository.save(product);
//        GenericResponseHandler<GenericProductDto> genericResponseHandler = new GenericResponseHandler<>();
//        genericResponseHandler.setData(genericProductDto);
//        genericResponseHandler.setMessage("Product updated sucessfully");
//        genericResponseHandler.setHttpStatus(HttpStatus.OK);
//        return genericResponseHandler;
//    }
@Override
public GenericResponseHandler<GenericProductDto> updateProduct(GenericProductDto genericProductDto) throws NotFoundException, IncompleteDetailsException {
    if (genericProductDto.getId() == null) {
        throw new IncompleteDetailsException("Please provide the product id which you want to update");
    }

    Optional<Product> productOptional = productRepository.findById(genericProductDto.getId());
    if (!productOptional.isPresent()) {
        throw new NotFoundException("No product is available with id " + genericProductDto.getId());
    }

    // Fetch the existing Product entity
    Product existingProduct = productOptional.get();


    // Update the existing Product with new details
    existingProduct.setTitle(genericProductDto.getTitle());
    existingProduct.setDescription(genericProductDto.getDescription());
    existingProduct.setPrice(genericProductDto.getPrice());
    existingProduct.setImage(genericProductDto.getImage());

    // Set the attached Category
    existingProduct.setCategory(genericProductDto.getCategory());

    // Save the updated Product
    productRepository.save(existingProduct);

    GenericResponseHandler<GenericProductDto> genericResponseHandler = new GenericResponseHandler<>();
    genericResponseHandler.setData(genericProductDto);
    genericResponseHandler.setMessage("Product updated successfully");
    genericResponseHandler.setHttpStatus(HttpStatus.OK);
    return genericResponseHandler;
}



}
