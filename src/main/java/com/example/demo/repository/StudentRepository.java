package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM students e WHERE e.delete_flag=false",nativeQuery = true)
    public List<Student> findAll();

    @Query(value = "SELECT * FROM students e WHERE e.delete_flag=true",nativeQuery = true)
    public List<Student> recycleBin();

    @Query(value = "SELECT * FROM students e WHERE e.id=:id",nativeQuery = true)
    public Student findStudentById(Long id);

    @Query(value = "UPDATE students e SET e.delete_flag=true WHERE e.id = :id", nativeQuery = true)
    @Transactional
    @Modifying
    public void softDelete(Long id);

    @Query(value = "SELECT * FROM students e WHERE CONCAT(e.first_name, e.last_name) LIKE %:keyword%",nativeQuery = true)
    List<Student> search(String keyword);
}
