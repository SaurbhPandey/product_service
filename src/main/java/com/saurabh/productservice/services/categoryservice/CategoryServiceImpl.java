package com.saurabh.productservice.services.categoryservice;

import com.saurabh.productservice.dtos.CategoryDto;
import com.saurabh.productservice.dtos.GenericProductDto;
import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.NotFoundException;
import com.saurabh.productservice.models.Category;
import com.saurabh.productservice.models.Product;
import com.saurabh.productservice.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    private GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setCategory(product.getCategory());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
//        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setId(product.getUuid());
        return genericProductDto;
    }
    private CategoryDto convertCategoryToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getUuid());
        List<GenericProductDto> productDtoList = new ArrayList<>();
//        category.getProductList().forEach(product -> {
////                    System.out.println(product);
//          productDtoList.add(convertProductToGenericProductDto(product));
//        });
        categoryDto.setTitle(category.getName());
//        categoryDto.setProductDtoList(productDtoList);
        return categoryDto;
    }


    @Override
    public GenericResponseHandler<CategoryDto> getCategoryById(UUID id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("There is no category available with category id " + id);
        }
        Category category = categoryOptional.get();
        CategoryDto categoryDto = convertCategoryToCategoryDto(category);
        GenericResponseHandler<CategoryDto> genericResponseHandler = new GenericResponseHandler<>();
        genericResponseHandler.setData(categoryDto);
        genericResponseHandler.setMessage("All category fetched successfully");
        genericResponseHandler.setHttpStatus(HttpStatus.OK);
        return genericResponseHandler;
    }

    @Override
    public GenericResponseHandler<CategoryDto> getCategoryByName(String name) throws NotFoundException {
       Category category = categoryRepository.findByName(name);
        if(category == null){
            throw new NotFoundException("There is no category available with this " + name + " name");
        }
        CategoryDto categoryDto = convertCategoryToCategoryDto(category);
        GenericResponseHandler<CategoryDto> genericResponseHandler = new GenericResponseHandler<>();
        genericResponseHandler.setData(categoryDto);
        genericResponseHandler.setHttpStatus(HttpStatus.OK);
        genericResponseHandler.setMessage("Category fetched successfully !!");
        return genericResponseHandler;
    }

    @Override
    public GenericResponseHandler<List<String>> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<String> categoryList = new ArrayList<>();
        categories.forEach(category -> {
            categoryList.add(category.getName());
        });
        GenericResponseHandler<List<String>> genericResponseHandler = new GenericResponseHandler<>();
        genericResponseHandler.setMessage("All category fetched successfully !!");
        genericResponseHandler.setData(categoryList);
        genericResponseHandler.setHttpStatus(HttpStatus.OK);
        return genericResponseHandler;
    }


}
