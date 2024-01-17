package com.saurabh.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Orders extends BaseModel{
    //    we can customize the mapping table according to ourself by using annotation @JoinTable
//    join column represent the name of the pk of the current entity
//    inverse join column represent the name of the pk of the other entity
    @JoinTable(
            name = "product_order_mapping",
            joinColumns = @JoinColumn(name = "Order_id"),
            inverseJoinColumns = @JoinColumn(name = "Product_id")
    )
    @ManyToMany
 private List<Product> productList;
}
