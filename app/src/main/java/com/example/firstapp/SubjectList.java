package com.example.firstapp;

public class SubjectList {
    private String SubjectName;
    private String SubjectCode;
    private String IdAllocations;

    public SubjectList(String subjectName, String subjectCode,String idAllocations) {
        SubjectName = subjectName;
        SubjectCode = subjectCode;
        IdAllocations = idAllocations;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        SubjectCode = subjectCode;
    }

    public String getIdAllocations() {
        return IdAllocations;
    }

    public void setIdAllocations(String idAllocations) {
        IdAllocations = idAllocations;
    }

    @Override
    public String toString() {
        return "SubjectList{" +
                "SubjectName='" + SubjectName + '\'' +
                ", SubjectCode='" + SubjectCode + '\'' +
                ", IdAllocations='" + IdAllocations + '\'' +
                '}';
    }
}
