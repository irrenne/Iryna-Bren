package com.epam.spring.homework.project.repository;

import com.epam.spring.homework.project.model.Report;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r WHERE r.user.id = ?1")
    List<Report> getReportsForUser(Long userId, Pageable pageable);
}
