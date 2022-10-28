package com.wang.onlineexam.domain;

import javax.persistence.*;

@Entity(name = "student_exam_rel")
public class StudentExamRel {

    @Column(name = "student_id")
    private long studentId;
    @Column(name = "exam_id")
    private long examId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    private String answeredPaper;
    private double score;
}
