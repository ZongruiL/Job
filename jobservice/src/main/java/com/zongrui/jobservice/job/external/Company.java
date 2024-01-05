package com.zongrui.jobservice.job.external;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.List;

public class Company {

    private Long id;
    private String name;
    private String description;





    public Company(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public  Company(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

