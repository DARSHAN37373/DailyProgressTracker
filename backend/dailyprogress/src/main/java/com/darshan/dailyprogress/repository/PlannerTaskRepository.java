package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.PlannerTask;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.darshan.dailyprogress.entity.PlannerStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlannerTaskRepository extends JpaRepository<PlannerTask, Long> {

    List<PlannerTask> findByUser(User user);
    
    Page<PlannerTask> findByUser(User user, Pageable pageable);

    

    // Filter Planner Tasks by Status
List<PlannerTask> findByUserAndStatus(
        User user,
        PlannerStatus status
);

// Search Planner Tasks by Title
List<PlannerTask> findByUserAndTitleContainingIgnoreCase(
        User user,
        String keyword
);

// Status + Title + Pagination + Sorting
Page<PlannerTask> findByUserAndStatusAndTitleContainingIgnoreCase(
        User user,
        PlannerStatus status,
        String keyword,
        Pageable pageable
);

    Optional<PlannerTask> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, PlannerStatus status);

    long countByUserAndDueDate(
        User user,
        LocalDate dueDate);

long countByUserAndStatusAndDueDate(
        User user,
        PlannerStatus status,
        LocalDate dueDate);
        
    @Query("""
        SELECT COALESCE(SUM(p.estimatedHours), 0)
        FROM PlannerTask p
        WHERE p.user = :user
        """)
Long sumEstimatedHoursByUser(@Param("user") User user);

@Query("""
        SELECT COALESCE(SUM(p.actualHours), 0)
        FROM PlannerTask p
        WHERE p.user = :user
        """)
Long sumActualHoursByUser(@Param("user") User user);
}