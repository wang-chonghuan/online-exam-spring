package com.wang.onlineexam.web;

import com.wang.onlineexam.service.ExamSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {
    @Autowired
    ExamSettingService examSettingService;
}
