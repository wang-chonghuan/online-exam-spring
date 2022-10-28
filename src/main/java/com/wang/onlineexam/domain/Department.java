package com.wang.onlineexam.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Department {

    // this property is not a field in the table department
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "department")
    private List<Teacher> teachersList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    private String name;



    public Department() {
    }

    public Department(long id, String name) {
        super();
        this.id = id;
        this.name = name;
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

    public List<Teacher> getTeachers() {
        return teachersList;
    }

    public void setTeachers(List<Teacher> teachersList) {
        this.teachersList = teachersList;
    }
}
