package com.example.demo.service;

import com.example.demo.entity.StudentScore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {


    List<StudentScore> getScoreById(Long id);

    StudentScore saveScore(StudentScore score);

    void deleteScoreById(Long id);

    StudentScore getByScoreId(Long id);

    StudentScore updateScore(StudentScore existingScore);


    //void deleteScoreWithStudent(Long id);
}
