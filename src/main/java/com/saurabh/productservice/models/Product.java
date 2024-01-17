package com.saurabh.productservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product extends BaseModel{
private String title;
private String description;
private String Image;
@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name = "category_guid")
private Category category;
@OneToOne(cascade = {CascadeType.PERSIST , CascadeType.REMOVE} )
private Price price;
}


//cascade type persist meaning is that if someone is trying to save the product and not save category and price
// then cascade help us to save the price and category also while the creation of product
// basically it help us to avoid the extra db calls