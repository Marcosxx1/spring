package com.estudos.restfullwebservices.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {

    public User() {
    }

    public User(Integer id, String name, String email, String password, Boolean isAdmin, LocalDate birthDate, String driverLicense, String cep, List<Car> cars, List<Post> post) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.birthDate = birthDate;
        this.driverLicense = driverLicense;
        this.cep = cep;
        this.cars = cars;
        this.post = post;
    }

    public User(int i, String adam, LocalDate localDate) {
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean isAdmin;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String driverLicense;

    @Column(nullable = false)
    private String cep;

    @OneToMany(mappedBy = "user") // One "user" ToMany cars
    @Valid
    private List<Car> cars;

    @OneToMany(mappedBy = "user")
    @Valid
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", birthDate=" + birthDate +
                ", driverLicense='" + driverLicense + '\'' +
                ", cep='" + cep + '\'' +
                ", cars=" + cars +
                ", post=" + post +
                '}';
    }
}
