package com.wang.onlineexam.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StudentExamRelationRepository extends CrudRepository<StudentExamRelation, Long> {
    List<StudentExamRelation> findByStudentAndExam(@Param("student")long student, @Param("exam")long exam);
}
