package com.saurabh.productservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Price extends BaseModel{
private String currency;
private double price;
}
