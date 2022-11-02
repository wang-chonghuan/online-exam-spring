package com.wang.onlineexam.service;

import com.wang.onlineexam.domain.Question;

import java.util.Arrays;
import java.util.List;

public class QuestionFactory {

    public static Base create(Question.QuestionType qtype) {
        switch (qtype) {
            case SINGLE -> {
                return new SingleChoice();
            }
            case MULTIPLE -> {
                return new MultipleChoice();
            }
            case WRITING -> {
                return new Writing();
            }
        }
        return null;
    }

    public static abstract class Base {
        public abstract double grade(String answer, String refAnswer, double mark);
    }

    public static class SingleChoice extends Base {

        @Override
        public double grade(String answer, String refAnswer, double mark) {
            if(answer.equals(refAnswer)) {
                return mark;
            } else {
                return 0.0;
            }
        }
    }

    public static class MultipleChoice extends Base {

        @Override
        public double grade(String answer, String refAnswer, double mark) {
            String[] answerArray = answer.split(",");
            List<String> refList = Arrays.asList(refAnswer.split(","));
            double eachMark = mark / Double.valueOf(refList.size());
            double finalScore = 0.0;
            for(String eachAnswer : answerArray) {
                if(refList.contains(eachAnswer)) { // one right, accumulate one choice's mark
                    finalScore += eachMark;
                } else { // one wrong, final score is 0
                    finalScore = 0.0;
                    break;
                }
            }
            return finalScore;
        }
    }

    public static class Writing extends Base {

        @Override
        public double grade(String answer, String refAnswer, double mark) {
            return 0.0;
        }
    }
}
