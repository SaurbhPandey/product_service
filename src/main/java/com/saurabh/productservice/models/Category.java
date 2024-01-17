package com.saurabh.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category extends BaseModel{
private String name;
@OneToMany(mappedBy = "category")
@JsonIgnore
private List<Product> productList;
}
