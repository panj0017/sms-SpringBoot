package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.StudentScore;
import com.example.demo.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;

	@Autowired
	private ScoreRepository scoreRepository;
	
	
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}



	@Override
	public List<Student> getAllStudents(String keyword){
		if(keyword != null){
			return studentRepository.search(keyword);
		}
		return studentRepository.findAll();
		
	}
	
	@Override
	public Student saveStudent(Student student){

		return studentRepository.save(student);
	}



	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		Optional<Student> student =  studentRepository.findById(id);
		Student stu = student.get();
		return stu;

		// retrun studentRepository.findById(id).get()
	}



	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}



	@Override
	public void deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		//studentRepository.deleteById(id);

		Student studentToBeDeleted = studentRepository.findStudentById(id);
		studentToBeDeleted.setDeleteFlag(true);
		studentRepository.save(studentToBeDeleted);

		List<StudentScore> scoreToBeDeleted = scoreRepository.findAllByStudentID(id);
		for (StudentScore s: scoreToBeDeleted){
			s.setDeleteFlag(true);
			scoreRepository.save(s);
		}


//		studentRepository.softDelete(id);
//		scoreRepository.softDelete(id);
//


		
	}



}
