package com.estudos.restfullwebservices.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue
    private UUID id;

    @Pattern(regexp = "[A-Z]{3}-\\d{4}", message = "license_plate must be in the format ABC-1234")
    private String license_plate;

    @NotEmpty(message = "car_brand is required")
    private String car_brand;

    @NotEmpty(message = "car_color is required")
    private String car_color;

    private LocalDateTime registration_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "specification_id")
    private Specification specification;


}
