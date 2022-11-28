package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import java.util.List;

@Controller
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping("/students")
	public String listStudents(@RequestParam(name = "key", required = false) String keyWord, Model model) {

		List<Student> list = studentService.getAllStudents(keyWord);
		model.addAttribute("students",list);
		model.addAttribute("keyword",keyWord);
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student",student);
		return "create_student";
		
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student";
		
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student){
        Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
    }
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
}
