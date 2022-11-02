package com.wang.onlineexam.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.onlineexam.domain.Exam;
import com.wang.onlineexam.service.ExamSettingService;
import com.wang.onlineexam.service.PaperService;
import com.wang.onlineexam.service.QuestionWrapper;
import com.wang.onlineexam.utils.AnyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExamController {
    @Autowired
    ExamSettingService examSettingService;
    @Autowired
    PaperService paperService;

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @RequestMapping(value="/create-blank-paper", method= RequestMethod.POST)
    public ResponseEntity<?> createBlankPaper(@RequestBody QuestionWrapper.ParamList req) throws JsonProcessingException {
        Exam exam = paperService.createBlankPaper(req.getExamId(), req.getParamList());
        String blankPaperJsonmap = AnyUtil.jsonmapToJsonstr(exam.getBlankPaper());
        logger.info(String.format("createBlankPaper:: examId: %d updated with blankPaper: %s", exam.getId(), blankPaperJsonmap));
        // 200 is the appropriate status code assuming the PUT only did an update and did not create a resource.
        return ResponseEntity.ok().body(blankPaperJsonmap);
    }

}
