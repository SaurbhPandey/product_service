package com.saurabh.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
// @MappedSuperclass annotation is used to define as a class a super class
// i.e , this class behaviour is common in all the class so that this class is not an entity itself
// it is just providing the common  behaviour to the other entities
public class BaseModel {
    @Id
    @GeneratedValue(generator = "saurabh")
    @GenericGenerator(name = "saurabh", strategy = "uuid2")
    @Column(name = "id", nullable = false, updatable = false)
    private UUID uuid;
}
