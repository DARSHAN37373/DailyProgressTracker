package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.ActivityStatus;
import com.darshan.dailyprogress.entity.DailyActivity;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface DailyActivityRepository extends JpaRepository<DailyActivity, Long> {

    List<DailyActivity> findByUser(User user);

    Page<DailyActivity> findByUser(User user, Pageable pageable);

    

    List<DailyActivity> findByUserAndStatusOrderByActivityDateAsc(
        User user,
        ActivityStatus status);

        // Filter Activities by Status
List<DailyActivity> findByUserAndStatus(
        User user,
        ActivityStatus status
);

// Search Activities by Title
List<DailyActivity> findByUserAndTitleContainingIgnoreCase(
        User user,
        String keyword
);

// Status + Title + Pagination + Sorting
Page<DailyActivity> findByUserAndStatusAndTitleContainingIgnoreCase(
        User user,
        ActivityStatus status,
        String keyword,
        Pageable pageable
);


    Optional<DailyActivity> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndActivityDateBetween(
        User user,
        LocalDate startDate,
        LocalDate endDate);

        long countByUserAndStatusAndActivityDateBetween(
        User user,
        ActivityStatus status,
        LocalDate startDate,
        LocalDate endDate);
}