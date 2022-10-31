package com.wang.onlineexam.service;

import com.wang.onlineexam.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaperService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentExamRelationRepository studentExamRelationRepository;

    // create paper
    public Exam createBlankPaperAndSave(long examId, List<QuestionWrapper.Param> paramList) {
        Exam exam = examRepository.findById(examId).get();

        List<QuestionWrapper> blankQuestionList = new ArrayList<>();
        for(QuestionWrapper.Param qParam : paramList) {
            Question question = questionRepository.findById(qParam.questionId).get();
            QuestionWrapper qw = new QuestionWrapper();
            qw.initByClass(question, qParam);
            blankQuestionList.add(qw);
        }
        if(blankQuestionList.isEmpty()) {
            return exam;
        } else {
            exam.setBlankPaper(QuestionWrapper.questionWrapperListToJsonmap(blankQuestionList, "blank_question_list"));
            exam.setExamStatus(Exam.ExamStatus.REGISTERING);
            Exam savedExam = examRepository.save(exam);
            return savedExam;
        }
    }

    // answer paper
    public void createAnsweredPaperAndSave(long studentId, long examId, List<QuestionWrapper.Param> paramList) {
        StudentExamRelation rel = studentExamRelationRepository.findByStudentAndExam(studentId, examId).stream().findFirst().get();

        List<QuestionWrapper> answeredQuestionList = new ArrayList<>();
        double scoreOfChoiceQuestions = 0.0;
        for(QuestionWrapper.Param qParam : paramList) {
            Question question = questionRepository.findById(qParam.questionId).get();
            QuestionWrapper qw = new QuestionWrapper();
            qw.initByClass(question, qParam);
            // grade the choice question
            qw.gradeIfChoiceQuestion(question);
            scoreOfChoiceQuestions += qw.score;
            // gen the question json
            answeredQuestionList.add(qw);
        }

        rel.setScore(scoreOfChoiceQuestions);
        rel.setPaperAnswered(QuestionWrapper.questionWrapperListToJsonmap(answeredQuestionList, "answered_question_list"));
        studentExamRelationRepository.save(rel);
    }

    // grade paper step 1
    // return all the writing questions to frontend, json format
    public Map<String, Object> findAllWritingQuestions(long studentId, long examId) {
        StudentExamRelation rel = studentExamRelationRepository.findByStudentAndExam(studentId, examId).stream().findFirst().get();
        Map<String, Object> answeredPaper = rel.getPaperAnswered();

        List<Object> questionWrapperList = (List<Object>)answeredPaper.get("answered_question_list");
        List<QuestionWrapper> writingQuestionList = new ArrayList<>();
        for(Object obj : questionWrapperList) {
            Map<String, Object> answeredQuestion = (Map<String, Object>)obj;
            QuestionWrapper qw = new QuestionWrapper();
            qw.initByJsonmap(answeredQuestion);
            if(qw.type == Question.QuestionType.WRITING) {
                writingQuestionList.add(qw);
            }
        }

        return QuestionWrapper.questionWrapperListToJsonmap(writingQuestionList, "writing_question_list");
    }

    // grade paper step 2
    public void saveWritingScores(long studentId, long examId, ArrayList<QuestionWrapper.Param> paramList) throws Exception {
        StudentExamRelation rel = studentExamRelationRepository.findByStudentAndExam(studentId, examId).stream().findFirst().get();
        Map<String, Object> answeredPaperJsonmap = rel.getPaperAnswered();
        List<QuestionWrapper> questionWrapperList = QuestionWrapper.jsonmapToQuestionWrapperList(answeredPaperJsonmap, "answered_question_list");
        double totalScore = rel.getScore();
        for(int indexParam = 0, indexWrapper = 0; indexWrapper <= questionWrapperList.size(); ) {
            QuestionWrapper qWrapper = questionWrapperList.get(indexWrapper);
            QuestionWrapper.Param qParam = paramList.get(indexParam);
            if(qWrapper.questionId != qParam.questionId) {
                ++indexWrapper;
            } else {
                qWrapper.score = qParam.score;
                totalScore += qWrapper.score;
                ++indexWrapper;
                ++indexParam;
            }
        }
        rel.setScore(totalScore);
        rel.setPaperAnswered(QuestionWrapper.questionWrapperListToJsonmap(questionWrapperList, "answered_question_list"));
        studentExamRelationRepository.save(rel);
    }
}
