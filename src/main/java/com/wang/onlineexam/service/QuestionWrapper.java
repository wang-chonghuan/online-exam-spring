package com.wang.onlineexam.service;

import com.wang.onlineexam.domain.Question;

import java.util.*;

public class QuestionWrapper {
    public long questionId;
    public Question.QuestionType type;
    public String refAnswer;
    public Map<String, Object> content;
    public int order;
    public double mark;
    public double score = 0.0;
    public String answer;

    public Map<String, Object> toJsonmap() {
        Map<String, Object> jsonmap = new Hashtable<>();
        jsonmap.put("question_id", questionId);
        jsonmap.put("type", type);
        jsonmap.put("ref_answer", refAnswer);
        jsonmap.put("content", content);
        jsonmap.put("order", order);
        jsonmap.put("mark", mark);
        jsonmap.put("score", score);
        jsonmap.put("answer", answer);
        return jsonmap;
    }

    public void initByJsonmap(Map<String, Object> jsonmap) {
        this.questionId = Long.valueOf(String.valueOf(jsonmap.get("question_id")));
        this.type = Question.QuestionType.values()[Integer.valueOf(String.valueOf(jsonmap.get("question_id")))];
        this.refAnswer = String.valueOf(jsonmap.get("ref_answer"));
        this.content = (Map<String, Object>) jsonmap.get("content");
        this.order = Integer.valueOf(String.valueOf(jsonmap.get("order")));
        this.mark = Double.valueOf(String.valueOf(jsonmap.get("mark")));
        this.score = Double.valueOf(String.valueOf(jsonmap.get("score")));
        this.answer = String.valueOf(jsonmap.get("answer"));
    }

    public void initByClass(Question q, Param qIdWrapper) {
        this.questionId = q.getId();
        this.type = q.getQuestionType();
        this.refAnswer = q.getRefAnswer();
        this.content = q.getQuestionContent();
        this.order = qIdWrapper.order;
        this.mark = qIdWrapper.mark;
        this.score = qIdWrapper.score;
        this.answer = qIdWrapper.answer;
    }

    public static Map<String, Object> questionWrapperListToJsonmap(List<QuestionWrapper> questionWrapperList, String tag) {
        Map<String, Object> jsonmap = new HashMap<>();
        List<Object> jsonlist = new ArrayList<>();
        for(QuestionWrapper qw : questionWrapperList) {
            jsonlist.add(qw);
        }
        jsonmap.put(tag, jsonlist);
        return jsonmap;
    }

    public static List<QuestionWrapper> jsonmapToQuestionWrapperList(Map<String, Object> jsonmap, String tag) {
        List<QuestionWrapper> qwList = new ArrayList<>();
        List<Object> jsonlist = (List<Object>)jsonmap.get(tag);
        for(Object obj : jsonlist) {
            Map<String, Object> elementJsonmap = (Map<String, Object>)obj;
            QuestionWrapper qw = new QuestionWrapper();
            qw.initByJsonmap(elementJsonmap);
            qwList.add(qw);
        }
        return qwList;
    }

    public int gradeIfChoiceQuestion(Question question) {
        if(question.getQuestionType() == Question.QuestionType.WRITING) {
            return 1;
        } else {
            score = (question.getRefAnswer().equals(answer) ? mark : 0.0);
            return 0;
        }
    }

    public class Param {
        public long questionId;
        public int order;
        public double mark;
        public double score;
        public String answer;
    }
}
