package com.saurabh.productservice.services.productservice;

import com.saurabh.productservice.dtos.GenericProductDto;
import com.saurabh.productservice.dtos.GenericResponseHandler;
import com.saurabh.productservice.exceptions.IncompleteDetailsException;
import com.saurabh.productservice.exceptions.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public GenericResponseHandler<GenericProductDto> getProductById(UUID id) throws NotFoundException;
    public GenericResponseHandler<GenericProductDto> createProduct(GenericProductDto genericProductDto) throws IncompleteDetailsException;
    public GenericResponseHandler<List<GenericProductDto>> getAllProduct();
    public GenericResponseHandler<GenericProductDto> deleteroductById(UUID id) throws NotFoundException;

    public GenericResponseHandler<GenericProductDto> updateProduct(GenericProductDto genericProductDto) throws NotFoundException , IncompleteDetailsException;
}
