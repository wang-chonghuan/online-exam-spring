package com.wang.onlineexam.domain;

import javax.persistence.*;

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
}
