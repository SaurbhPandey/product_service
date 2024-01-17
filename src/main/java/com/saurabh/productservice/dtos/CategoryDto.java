package com.saurabh.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryDto {
    private UUID id;
    private String title;
    private List<GenericProductDto> productDtoList;
}
