package com.wang.onlineexam.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * for using JSON here
 * <dependency>
 * 	<groupId>com.vladmihalcea</groupId>
 * 	<artifactId>hibernate-types-52</artifactId>
 * 	<version>2.12.0</version>
 * </dependency>
 */
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToMany(mappedBy = "exam")
    private Set<StudentExamRelation> studentExamRelations = new HashSet<>();

    private String title;
    private String description;
    private String location;
    private LocalDateTime publishTime;
    private LocalDateTime examTime;
    private int durationSeconds;
    private int status; // 1 setting-up, 2 registering, 3 grading, 4 finished

    @Type(type = "json")
    @Column(name = "paper_content", columnDefinition = "json")
    private Map<String,Object> paperContent = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
