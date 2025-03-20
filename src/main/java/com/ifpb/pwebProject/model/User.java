package com.ifpb.pwebProject.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    private String id;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Activity> activities;

    public User(String id) {
        this.id = id;
    }

    public User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Activity> getActivities() {
        return activities;
    }

}
