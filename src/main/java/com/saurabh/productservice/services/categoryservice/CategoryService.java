package com.saurabh.productservice.services.categoryservice;

import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.NotFoundException;
import com.saurabh.productservice.models.Category;

import java.util.UUID;

public interface CategoryService {
    public GenericResponseHandler<Category> getCategoryById(UUID id) throws NotFoundException;
}
