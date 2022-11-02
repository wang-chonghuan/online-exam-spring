package com.wang.onlineexam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.onlineexam.domain.*;
import com.wang.onlineexam.service.PaperService;
import com.wang.onlineexam.service.QuestionWrapper;
import com.wang.onlineexam.utils.AnyUtil;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PaperServiceTest {
    @Autowired
    private PaperService paperService;

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentExamRelationRepository studentExamRelationRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(OnlineExamApplication.class);

    private final String correctResultForFindAllWritingQuestions = "{\"writing_question_list\":[{\"questionId\":3,\"type\":\"WRITING\",\"refAnswer\":\"Robert Griesemer, Rob Pike, and Ken Thompson\",\"content\":{\"statement\":\"Who created golang?\"},\"order\":3,\"mark\":2.0,\"score\":0.0,\"answer\":\"Robe Pike\"}]}";

    @Test
    void testCreateBlankPaperAndSave() {
        Exam exam = examRepository.findByTitle("CS5741-midtern-exam").stream().findFirst().get();
        Exam examWithBlankPaper = paperService.createBlankPaperAndSave(exam.getId(), Arrays.asList(
            new QuestionWrapper.Param(questionRepository.findById(1L).get().getId(), 1, 5, 0, ""),
            new QuestionWrapper.Param(questionRepository.findById(2L).get().getId(), 2, 3, 0, ""),
            new QuestionWrapper.Param(questionRepository.findById(3L).get().getId(), 3, 2, 0, "")
        ));
        assertThat(examRepository.findById(examWithBlankPaper.getId()).get().getExamStatus() == Exam.ExamStatus.REGISTERING).isTrue();
    }

    @Test
    void testCreateAnsweredPaperAndSave() {
        StudentExamRelation rel = paperService.createAnsweredPaperAndSave(1L, 1L, Arrays.asList(
            new QuestionWrapper.Param(questionRepository.findById(1L).get().getId(), 1, 5, 0, "B"),
            new QuestionWrapper.Param(questionRepository.findById(2L).get().getId(), 2, 3, 0, "A,B,D"),
            new QuestionWrapper.Param(questionRepository.findById(3L).get().getId(), 3, 2, 0, "Robe Pike")
        ));
        StudentExamRelation retRel = studentExamRelationRepository.findByStudentIdAndExamId(1L, 1L).stream().findFirst().get();
        assertThat(retRel.getScore() == 8.0).isTrue();
    }

    @Test
    void testFindAllWritingQuestions() throws Exception {

        // todo! for better solution? the first two tests must be performed to get data for this test
        testCreateBlankPaperAndSave();
        testCreateAnsweredPaperAndSave();

        Map<String, Object> allWritingQsJsonmap = new HashMap<>();
        allWritingQsJsonmap = paperService.findAllWritingQuestions(1L, 1L);
        String allWritingQsJsonstr = AnyUtil.jsonmapToJsonstr(allWritingQsJsonmap);
        //logger.info(allWritingQsJsonstr);
        //logger.info(correctResultForFindAllWritingQuestions);
        // todo! here is a problem: allWritingQsJsonstr's double value is 2.0,
        // todo! but when i convert it to string use the website, it becomes 2, idk which one is correct
        assertThat(allWritingQsJsonstr.equals(correctResultForFindAllWritingQuestions)).isTrue();
    }
}


