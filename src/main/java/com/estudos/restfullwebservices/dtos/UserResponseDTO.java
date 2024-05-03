package com.estudos.restfullwebservices.dtos;

import com.estudos.restfullwebservices.model.Car;
import com.estudos.restfullwebservices.model.Post;

import java.time.LocalDate;
import java.util.List;

public class UserResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean is_admin;
    private LocalDate birth_date;
    private String DriverLicense;
    private String cep;
    private List<Car> cars;
    private List<Post> post;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getDriverLicense() {
        return DriverLicense;
    }

    public void setDriverLicense(String driver_license) {
        this.DriverLicense = driver_license;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
