package com.example.firstapp;

import androidx.annotation.NonNull;

public class ExamList {
    String Exam;
    int eMark,tMark;

    public ExamList(String exam, int eMark, int tMark) {
        Exam = exam;
        this.eMark = eMark;
        this.tMark = tMark;
    }

    public String getExam() {
        return Exam;
    }

    public void setExam(String exam) {
        Exam = exam;
    }

    public int geteMark() {
        return eMark;
    }

    public void seteMark(int eMark) {
        this.eMark = eMark;
    }

    public int gettMark() {
        return tMark;
    }

    public void settMark(int tMark) {
        this.tMark = tMark;
    }

    @Override
    public String toString() {
        return "ExamList{" +
                "Exam='" + Exam + '\'' +
                ", eMark=" + eMark +
                ", tMark=" + tMark +
                '}';
    }
}
