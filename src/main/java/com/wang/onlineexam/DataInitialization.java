package com.wang.onlineexam;

import com.wang.onlineexam.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitialization {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(DataInitialization.class);
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionBankRepository questionBankRepository;
    @Autowired
    private StudentExamRelationRepository studentExamRelationRepository;

    // insert basic records for developing and testing
    public void init() {
        // insert teachers
        Teacher teacher1 = new Teacher("teacher1.name", "teacher1.email");
        Teacher teacher2 = new Teacher("teacher2.name", "teacher2.email");
        teacherRepository.saveAll(Arrays.asList(teacher1, teacher2));
        // insert students
        Student student1 = new Student("student1.name", "student1.email");
        Student student2 = new Student("student2.name", "student2.email");
        studentRepository.saveAll(Arrays.asList(student1, student2));
        // get teacher1's id
        List<Teacher> queryTeacherList = teacherRepository.findByEmail("teacher1.email");
        Optional<Teacher> queryTeacher1 = queryTeacherList.stream().findFirst();
        long idTeacher1 = queryTeacher1.get().getId();
        logger.debug("teacher1's id is: " + idTeacher1);
        // insert course1 for teacher1
        Course course1 = new Course("golang", Course.CourseStatus.ACTIVE, teacherRepository.findById(idTeacher1).get());
        courseRepository.save(course1);
    }
}
