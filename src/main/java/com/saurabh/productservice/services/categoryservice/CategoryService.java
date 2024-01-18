package com.saurabh.productservice.services.categoryservice;

import com.saurabh.productservice.dtos.CategoryDto;
import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.NotFoundException;
import com.saurabh.productservice.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    public GenericResponseHandler<CategoryDto> getCategoryById(UUID id) throws NotFoundException;
    public GenericResponseHandler<CategoryDto> getCategoryByName(String name) throws NotFoundException;
    public GenericResponseHandler<List<String>> getAllCategory();
}
