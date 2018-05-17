package com.castle.UserRegistrationSystem.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "UserDtos")
public class UserDTO {

    @javax.persistence.Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long Id;

    @NotEmpty(message = "error.name.empty")
    @Length(max = 50,message = "error.name.length")
    @Column(name = "NAME")
    private String name;

    @NotEmpty(message = "error.address.empty")
    @Length(max = 150,message = "error.address.length")
    @Column(name = "ADDRESS")
    private String address;

    @Email(message = "error.email.email")
    @NotEmpty(message = "error.email.empty")
    @Length(max = 80,message = "error.email.length")
    @Column(name = "EMAIL")
    private String email;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
