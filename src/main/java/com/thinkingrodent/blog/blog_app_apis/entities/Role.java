package com.thinkingrodent.blog.blog_app_apis.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

}
