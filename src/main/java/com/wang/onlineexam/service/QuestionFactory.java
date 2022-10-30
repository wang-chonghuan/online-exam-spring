package com.wang.onlineexam.service;

import com.wang.onlineexam.domain.Question;
import com.wang.onlineexam.utils.AnyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionFactory {
    public QuestionContentTemplate createQuestion(Question.QuestionType type) {
        switch (type) {
            case SINGLE:
            case MULTIPLE:
                return new QuestionChoiceTemplate();
            case WRITING:
                return new QuestionWritingTemplate();
            default:
                return null;
        }
    }
    public abstract class QuestionContentTemplate {
        public String jsonClass;
        public Question.QuestionType type;
        public String statement;
        public double mark;
        // template pattern
        public void init(Map<String, Object> qTemplateJsonMap, double mark) {
            this.type = Question.QuestionType.valueOf(String.valueOf(qTemplateJsonMap.get("type")));
            this.mark = mark;
            this.statement = String.valueOf(qTemplateJsonMap.get("statement"));
            this.initSub(qTemplateJsonMap);
        }
        protected abstract void initSub(Map<String, Object> qTemplateJsonMap);
    }

    /**
     {
     "json_class":"QuestionChoiceTemplate",
     "type":"single",
     "statement":"which year is golang first released?",
     "choices":{"A":"2001", "B":"2002", "C":"2007", "D":"2011"}
     }

     {
     "json_class":"QuestionChoiceTemplate",
     "type":"multiple",
     "statement":"which year is golang first released?",
     "choices":{"A":"2001", "B":"2002", "C":"2007", "D":"2011", "E":"2019"}
     }
     */
    public class QuestionChoiceTemplate extends QuestionContentTemplate {
        public Map<String, String> choices = new HashMap<>();
        public void initSub(Map<String, Object> qTemplateJsonMap) {
            this.jsonClass = this.getClass().getName();
            this.choices = AnyUtil.objToMapSs(qTemplateJsonMap.get("choices"));
        }
    }

    /**
     {
     "json_class":"QuestionWritingTemplate",
     "type":"writing",
     "statement":"Who created golang?",
     "rows":2
     }
     */
    public class QuestionWritingTemplate extends QuestionContentTemplate {
        public int rows;
        public void initSub(Map<String, Object> qTemplateJsonMap) {
            this.jsonClass = this.getClass().getName();
            this.rows = Integer.valueOf((Integer) qTemplateJsonMap.get("rows"));
        }
    }
}
