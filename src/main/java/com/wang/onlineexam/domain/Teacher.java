package com.wang.onlineexam.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "teacher")
    private List<Course> courseList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    private String name;
    private String email;


    public Teacher() {
    }

    public Teacher(long id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
