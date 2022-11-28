package com.example.demo.service.impl;

import com.example.demo.entity.StudentScore;
import com.example.demo.repository.ScoreRepository;
import com.example.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository ScoreRepository;

    public ScoreServiceImpl(com.example.demo.repository.ScoreRepository scoreRepository) {
        super();
        ScoreRepository = scoreRepository;
    }

    @Override
    public List<StudentScore> getScoreById(Long StudentID) {
        return ScoreRepository.findAllByStudentID(StudentID);
    }

    @Override
    public StudentScore saveScore(StudentScore score) {
        return ScoreRepository.save(score);
    }

    @Override
    public void deleteScoreById(Long id) {
        ScoreRepository.deleteById(id);
    }

    @Override
    public StudentScore getByScoreId(Long id) {
        return ScoreRepository.getById(id);
    }

    @Override
    public StudentScore updateScore(StudentScore existingScore) {
        return ScoreRepository.save(existingScore);
    }

//    @Override
//    public void deleteScoreWithStudent(Long id) {
//
//        List<StudentScore> allScore = ScoreRepository.findAllByStudentID(id);
//        ScoreRepository.deleteAll(allScore);
//
//    }
}
