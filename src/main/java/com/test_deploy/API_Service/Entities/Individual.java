package com.test_deploy.API_Service.Entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


/* */

@Entity
public class Individual {
    private @Id
    @GeneratedValue 
    Long id;
    private String name;
    private String email;
    private String phoneNumber;




    public Individual() {}

    public Individual(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email, this.phoneNumber);
    }

    @Override
    public String toString() {
        return "Individual{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + ", phoneNumber='" + this.phoneNumber + '\'' + '}';
    }

}


