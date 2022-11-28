package com.example.demo.repository;

import com.example.demo.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<StudentScore,Long> {

    @Query(value = "SELECT * FROM score s WHERE s.StudentID = :StudentID", nativeQuery = true)
    List<StudentScore> findAllByStudentID(Long StudentID);

    @Override
    @Query(value = "SELECT * FROM score e WHERE e.delete_flag=false",nativeQuery = true)
    public List<StudentScore> findAll();

    @Query(value = "SELECT * FROM score e WHERE e.delete_flag=true",nativeQuery = true)
    public List<StudentScore> recycleBin();

    @Query(value = "UPDATE score e SET e.delete_flag=true WHERE e.id = :studentId",nativeQuery = true)
    @Transactional
    @Modifying
    public void softDelete(Long studentId);

}
