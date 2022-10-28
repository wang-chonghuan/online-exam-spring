package com.wang.onlineexam.domain;

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

    private int QuestionType; // 1 single choice, 2 multiple choice, 3 writing

    @Type(type = "json")
    @Column(name = "question_statement", columnDefinition = "json")
    private Map<String,Object> questionStatement = new HashMap<>();

    private String referencedAnswer;

    private String tags; // one question has many tags, can be selected by regularexpressions
}
