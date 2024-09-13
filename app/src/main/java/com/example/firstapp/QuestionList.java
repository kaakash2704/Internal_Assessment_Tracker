package com.example.firstapp;

public class QuestionList {
    String questions;
    int qid;
    String exam;

    public QuestionList(String questions, int qid, String exam) {
        this.questions = questions;
        this.qid = qid;
        this.exam = exam;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {
        return "QuestionList{" +
                "questions='" + questions + '\'' +
                ", qid=" + qid +
                ", exam='" + exam + '\'' +
                '}';
    }
}
