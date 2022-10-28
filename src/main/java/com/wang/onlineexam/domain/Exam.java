package com.wang.onlineexam.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "exam")
public class Exam {

    //@ManyToMany(mappedBy = "student_exam_rel")
    //private List<Student> studentList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    private String description;
    private Timestamp publishTime;
    private Timestamp examTime;
    private int DurationSeconds;
    private int status; // 1 setting-up, 2 registering, 3 grading, 4 finished
/*
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private String paperContent;
*/
    public Exam() {
    }

    public Exam(long id, String description, Timestamp publishTime, Timestamp examTime, int durationSeconds, int status, String paperContent) {
        super();
        this.id = id;
        this.description = description;
        this.publishTime = publishTime;
        this.examTime = examTime;
        DurationSeconds = durationSeconds;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Timestamp getExamTime() {
        return examTime;
    }

    public void setExamTime(Timestamp examTime) {
        this.examTime = examTime;
    }

    public int getDurationSeconds() {
        return DurationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        DurationSeconds = durationSeconds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
