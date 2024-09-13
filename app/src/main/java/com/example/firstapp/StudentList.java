package com.example.firstapp;

public class StudentList {
    String student_name;
    String unique_id;

    float mark;

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public StudentList(String student_name, String unique_id) {
        this.student_name = student_name;
        this.unique_id = unique_id;
        mark=0;
    }


    public StudentList(String student_name, String unique_id, float v) {
        this.student_name = student_name;
        this.unique_id = unique_id;
        mark=v;
    }


    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    @Override
    public String toString() {
        return "Students{" +
                "student_name='" + student_name + '\'' +
                ", unique_id='" + unique_id + '\'' +
                '}';
    }
}
