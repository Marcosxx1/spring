package com.estudos.restfullwebservices.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@Entity
public class Specification {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "description is required")
    private String description;

    @OneToMany(mappedBy = "specification")
    @Valid
    private List<Car> cars;
}
