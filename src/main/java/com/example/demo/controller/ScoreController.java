package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.StudentScore;
import com.example.demo.service.ScoreService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private StudentService studentService;

    public ScoreController() {}

    public ScoreController(ScoreService scoreService) {
        super();
        this.scoreService = scoreService;
    }

    @GetMapping("/students/score/{StudentID}")
    public String ShowStudentScore(@PathVariable Long StudentID, Model model){
        List<StudentScore> scoreList = scoreService.getScoreById(StudentID);
        Student student = studentService.getStudentById(StudentID);
        model.addAttribute("abc", scoreList );
        model.addAttribute("student",  student);

        return "show_score";
    }

    @GetMapping("/students/newScore/{StudentID}")
    public String addStudentNewScore(@PathVariable Long StudentID, Model model){
        StudentScore studentScore = new StudentScore();
        studentScore.setStudentID(StudentID);
        model.addAttribute("nameabc",studentScore);
        return "create_newScore";
    }

    @PostMapping("/students/score/{StudentID}")
    public String saveStudentScore(@PathVariable Long StudentID, @ModelAttribute("nameabc") StudentScore score) {
        score.setStudentID(StudentID);
        scoreService.saveScore(score);
        return "redirect:/students/score/{StudentID}";
    }

    @GetMapping("/students/score/edit/{id}")
    public String getUpdateScoreForm(@PathVariable Long id, Model model){
        model.addAttribute("Kangaroo",scoreService.getByScoreId(id));
        return "edit_score";
    }

    @PostMapping("/students/studentScore/{id}")
    public String updateScore(@PathVariable Long id, @ModelAttribute("Kangaroo") StudentScore skr) {
        StudentScore existingScore = scoreService.getByScoreId(id);
        existingScore.setId(id);
        existingScore.setSubject(skr.getSubject());
        existingScore.setScore(skr.getScore());
        existingScore.setTeacher(skr.getTeacher());

        scoreService.updateScore(existingScore);
        return "redirect:/students/score/" + existingScore.getStudentID();
    }

    @GetMapping("/students/score/{StudentID}/{id}")
    public String deleteScore(@PathVariable Long id){

        scoreService.deleteScoreById(id);
        return  "redirect:/students/score/{StudentID}";
    }

}
