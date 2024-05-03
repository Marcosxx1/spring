package com.estudos.restfullwebservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "description is required")
    private String description;

    @OneToMany(mappedBy = "category")
    @Valid
    private List<Car> cars;
}
