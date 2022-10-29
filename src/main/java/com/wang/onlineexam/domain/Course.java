package com.wang.onlineexam.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String name;
    @Enumerated(EnumType.ORDINAL)
    private CourseStatus courseStatus;

    public enum CourseStatus {
        ACTIVE, FINISHED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "COURSE_STUDENT_RELATION",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID")
    )
    private Set<Student> students = new HashSet<>();

    public Course() {
    }

    public Course(String name, CourseStatus courseStatus, Teacher teacher) {
        this.name = name;
        this.courseStatus = courseStatus;
        this.teacher = teacher;
    }

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

    public CourseStatus getStatus() {
        return courseStatus;
    }

    public void setStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
