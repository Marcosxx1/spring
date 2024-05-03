package com.estudos.restfullwebservices.dtos;

import com.estudos.restfullwebservices.model.Car;
import com.estudos.restfullwebservices.model.Post;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class UserCreateDTO {
    @NotEmpty(message = "name is required")
    private String name;

    @Email
    @NotEmpty(message = "e-mail is required")
    private String email;

    @Min(8)
    @NotEmpty(message = "password is required")
    private String password;

    private Boolean isAdmin;

    @Past(message = "birthDate must be in the past")
    private LocalDate birthDate;

    @NotEmpty(message = "driverLicense is required")
    @Pattern(regexp = "[0-9]{9}", message = "driverLicense must contain 9 digits")
    private String driverLicense;

    @NotEmpty(message = "cep is required")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "cep must be in the format XXXXX-XXX")
    private String cep;

    @OneToMany(mappedBy = "user") // One "user" ToMany cars
    @Valid
    private List<Car> cars;

    @OneToMany(mappedBy = "user")
    @Valid
    private List<Post> post;

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
        return "UserCreateDTO{" +
                "name='" + name + '\'' +
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
