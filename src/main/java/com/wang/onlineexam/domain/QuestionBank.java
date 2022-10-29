package com.wang.onlineexam.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class QuestionBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private QuestionType questionType;

    @Type(type = "json")
    @Column(name = "question_statement", columnDefinition = "json")
    private Map<String,Object> questionStatement = new HashMap<>();

    @Type(type = "json")
    @Column(name = "referenced_answer", columnDefinition = "json")
    private Map<String,Object> referencedAnswer = new HashMap<>();

    @NotNull
    private String tags; // one question has many tags, can be selected by regular-expressions

    public enum QuestionType {
        SINGLE, MULTIPLE, WRITING
    }

    public QuestionBank(QuestionType questionType, Map<String, Object> questionStatement, Map<String, Object> referencedAnswer, String tags) {
        this.questionType = questionType;
        this.questionStatement = questionStatement;
        this.referencedAnswer = referencedAnswer;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Map<String, Object> getQuestionStatement() {
        return questionStatement;
    }

    public void setQuestionStatement(Map<String, Object> questionStatement) {
        this.questionStatement = questionStatement;
    }

    public Map<String, Object> getReferencedAnswer() {
        return referencedAnswer;
    }

    public void setReferencedAnswer(Map<String, Object> referencedAnswer) {
        this.referencedAnswer = referencedAnswer;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
