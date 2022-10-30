package com.wang.onlineexam.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private QuestionType questionType;

    @Type(type = "json")
    @Column(name = "question_content", columnDefinition = "json")
    private Map<String,Object> questionContent = new HashMap<>();

    @Type(type = "json")
    @Column(name = "referenced_answer", columnDefinition = "json")
    private Map<String,Object> referencedAnswer = new HashMap<>();

    @NotNull
    private String tags; // one question has many tags, can be selected by regular-expressions

    public enum QuestionType {
        SINGLE, MULTIPLE, WRITING
    }

    public Question(QuestionType questionType, Map<String, Object> questionContent, Map<String, Object> referencedAnswer, String tags) {
        this.questionType = questionType;
        this.questionContent = questionContent;
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

    public Map<String, Object> getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(Map<String, Object> questionContent) {
        this.questionContent = questionContent;
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

    public Question() {
    }
}
