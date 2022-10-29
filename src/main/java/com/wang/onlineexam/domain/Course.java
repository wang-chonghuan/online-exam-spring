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
    private int status; // 1 active, 2 finished

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
}
