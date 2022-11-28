package com.example.demo.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "score")
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String subject;

    @Column
    private int score;

    @Column
    private String teacher;

    @Column
    private Long StudentID;

    @Column
    private boolean deleteFlag = false;


    public StudentScore() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStudentID(Long studentID) {
        StudentID = studentID;
    }

    public Long getStudentID() {
        return StudentID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public StudentScore(String subject, int score, String teacher) {
        super();
        this.subject = subject;
        this.score = score;
        this.teacher = teacher;
    }

}
