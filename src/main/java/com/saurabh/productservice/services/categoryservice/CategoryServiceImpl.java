package com.saurabh.productservice.services.categoryservice;

import com.saurabh.productservice.dtos.CategoryDto;
import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.NotFoundException;
import com.saurabh.productservice.models.Category;
import com.saurabh.productservice.repository.CategoryRepository;

import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    @Override
    public GenericResponseHandler<Category> getCategoryById(UUID id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("There is no category availbale with category id " + id);
        }
        Category category = categoryOptional.get();
        return null;
    }
}
