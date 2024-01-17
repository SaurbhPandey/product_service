package com.saurabh.productservice.dtos;

import com.saurabh.productservice.models.Category;
import com.saurabh.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    public UUID id;
    private String title;
    private String description;
    private String image;
    private Category category;
    private Price price;
}
