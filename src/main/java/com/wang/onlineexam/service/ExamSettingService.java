package com.wang.onlineexam.service;

import com.wang.onlineexam.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ExamSettingService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentExamRelationRepository studentExamRelationRepository;


    public long setupExamSpec(ReqExamSpec examSpec) {
        Optional<Course> course = courseRepository.findById(examSpec.getCourseId());
        Exam exam = new Exam(
                course.get(),
                examSpec.getTitle(),
                examSpec.getDescription(),
                examSpec.getLocation(),
                examSpec.getPublishTime(),
                examSpec.getExamTime(),
                examSpec.getDurationSeconds(),
                Exam.ExamStatus.SETTING);
        Exam retExam = examRepository.save(exam);
        return retExam.getId();
    }

    public void setupExamPaper(long examId, ArrayList<QuestionWrapper> qWrapperList) throws Exception {
        Exam exam = examRepository.findById(examId).get();
        if(exam.getExamStatus() != Exam.ExamStatus.SETTING) {
            throw new Exception(String.format("status of examId %d must be SETTING", examId));
        }
        Map<String, Object> paperContent = new HashMap<>();
        QuestionFactory questionFactory = new QuestionFactory();
        List<QuestionFactory.QuestionContentTemplate> qTemplateList = new ArrayList<>();
        // many json(individual questions) to class, many class to class array, class array to json
        // many map to map list, map list to json
        for(QuestionWrapper qWrapper : qWrapperList) {
            Question question = questionRepository.findById(qWrapper.questionId).get();
            Map<String, Object> qContentJsonMap = question.getQuestionContent();
            QuestionFactory.QuestionContentTemplate qTemplate= questionFactory.createQuestion(question.getQuestionType());
            qTemplate.init(qContentJsonMap, qWrapper.mark);
            qTemplateList.add(qTemplate);
        }

    }

    public void setupExamStudents(long examId, ArrayList<Long> studentIdList) throws Exception {
        Exam exam = examRepository.findById(examId).get();
        if(exam.getExamStatus() != Exam.ExamStatus.SETTING) {
            throw new Exception(String.format("status of examId %d must be SETTING", examId));
        }
        ArrayList<StudentExamRelation> relationList = new ArrayList<>();
        for(Long studentId : studentIdList) {
            Student student = studentRepository.findById(studentId).get();
            // TODO!!! need to test if student is really equal to the one in exam, bcz of the equal()
            if(exam.getCourse().getStudents().contains(student)) {
                throw new Exception(String.format("studentId %d is not in the course of examId %d", studentId, examId));
            }
            StudentExamRelation studentExamRelation = new StudentExamRelation(student, exam);
            relationList.add(studentExamRelation);
        }
        studentExamRelationRepository.saveAll(relationList);
    }

    public class ReqExamSpec {
        private long courseId;
        private String title;
        private String description;
        private String location;
        private LocalDateTime publishTime;
        private LocalDateTime examTime;
        private int durationSeconds;

        public long getCourseId() {
            return courseId;
        }

        public void setCourseId(long courseId) {
            this.courseId = courseId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public LocalDateTime getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(LocalDateTime publishTime) {
            this.publishTime = publishTime;
        }

        public LocalDateTime getExamTime() {
            return examTime;
        }

        public void setExamTime(LocalDateTime examTime) {
            this.examTime = examTime;
        }

        public int getDurationSeconds() {
            return durationSeconds;
        }

        public void setDurationSeconds(int durationSeconds) {
            this.durationSeconds = durationSeconds;
        }
    }
    public class QuestionWrapper {
        public long questionId;
        public double mark;
        public int order;
    }

}
