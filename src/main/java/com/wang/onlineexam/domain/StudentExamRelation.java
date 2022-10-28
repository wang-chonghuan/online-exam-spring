package com.wang.onlineexam.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_exam_relation")
@org.hibernate.annotations.Immutable
public class StudentExamRelation {

    private double score;

    // how to reflect it to Json type in db?
    private String answeredPapaer;

    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "STUDENT_ID")
        private Long studentId;
        @Column(name = "EXAM_ID")
        private Long examId;

        public Id() {
        }

        public Id(Long studentId, Long itemId) {
            this.studentId = studentId;
            this.examId = examId;
        }
        //implementing equals and hashCode
    }

    @EmbeddedId
    private Id id = new Id();

    @Column(updatable = false)
    @NotNull
    private String addedBy;

    @Column(updatable = false)
    @NotNull
    @CreationTimestamp
    private LocalDateTime addedOn;

    @ManyToOne
    @JoinColumn(
            name = "STUDENT_ID",
            insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(
            name = "EXAM_ID",
            insertable = false, updatable = false)
    private Exam exam;

    public StudentExamRelation(
            String addedByUsername,
            Student student,
            Exam exam) {
        this.addedBy = addedByUsername;
        this.student = student;
        this.exam = exam;
        this.id.studentId = student.getId();
        this.id.examId = exam.getId();
        //category.addCategorizedItem(this);
        //item.addCategorizedItem(this);
    }
    // ...

    public StudentExamRelation() {

    }
}
