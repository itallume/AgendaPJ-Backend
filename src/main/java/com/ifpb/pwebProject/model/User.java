package com.ifpb.pwebProject.model;

import jakarta.persistence.*;


import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Activity> activities;

    public User(UUID id) {
        this.id = id;
    }

    public User(){

    }

    public User(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(UUID id, String name, String email, String password, List<Activity> activities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.activities = activities;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
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
}
