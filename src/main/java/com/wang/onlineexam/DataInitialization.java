package com.wang.onlineexam;

import com.wang.onlineexam.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        // insert course1 for course1
        Course course1 = new Course("CS5741-22", Course.CourseStatus.ACTIVE, teacherRepository.findById(idTeacher1).get());
        Student queryStudent1 = studentRepository.findByEmail("student1.email").stream().findFirst().get();
        Student queryStudent2 = studentRepository.findByEmail("student2.email").stream().findFirst().get();
        course1.getStudents().add(queryStudent1); // autogen record in table StudentCourseRelation
        course1.getStudents().add(queryStudent2);
        courseRepository.save(course1);
        // insert exam1 to course1
        LocalDateTime examTime = LocalDateTime.of(2022, 12, 31, 18, 59, 59);
        Course queryCourse1 = courseRepository.findByName("CS5741-22").stream().findFirst().get();
        Exam exam1withoutStudentsAndPaper = new Exam(
                queryCourse1, "course1 golang midterm exam1", "10% of the final score",
                "CS025", LocalDateTime.now(), examTime, 2400, Exam.ExamStatus.SETTING);
        examRepository.save(exam1withoutStudentsAndPaper);
    }
}
