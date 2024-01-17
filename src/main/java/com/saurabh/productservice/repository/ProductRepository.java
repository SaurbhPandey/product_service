package com.saurabh.productservice.repository;

import com.saurabh.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product , UUID> {
    Optional<Product> findById(UUID uuid);
    Product save(Product product);
    List<Product> findAll();
    void deleteById(UUID uuid);
}
