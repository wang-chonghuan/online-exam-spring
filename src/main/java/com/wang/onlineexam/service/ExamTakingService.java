package com.wang.onlineexam.service;

import com.wang.onlineexam.domain.ExamRepository;
import com.wang.onlineexam.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamTakingService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
}
