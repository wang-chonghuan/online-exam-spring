package com.wang.onlineexam;

import com.wang.onlineexam.domain.*;
import com.wang.onlineexam.service.PaperService;
import com.wang.onlineexam.service.QuestionWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PaperServiceTest {
    @Autowired
    private PaperService paperService;

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testCreateBlankPaperAndSave() {
        Exam exam = examRepository.findByTitle("CS5741-midtern-exam").stream().findFirst().get();
        Exam examWithBlankPaper = paperService.createBlankPaperAndSave(exam.getId(), Arrays.asList(
                new QuestionWrapper.Param(questionRepository.findById(1L).get().getId(), 1, 5, 0, ""),
                new QuestionWrapper.Param(questionRepository.findById(2L).get().getId(), 2, 3, 0, ""),
                new QuestionWrapper.Param(questionRepository.findById(3L).get().getId(), 3, 2, 0, "")));
        assertThat(examRepository.findById(examWithBlankPaper.getId()).get().getExamStatus() == Exam.ExamStatus.REGISTERING).isTrue();
    }
}


