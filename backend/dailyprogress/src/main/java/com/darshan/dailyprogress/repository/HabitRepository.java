package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.Habit;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.darshan.dailyprogress.entity.HabitStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findByUser(User user);

    Page<Habit> findByUser(User user, Pageable pageable);

    // Filter Habits by Status
List<Habit> findByUserAndStatus(
        User user,
        HabitStatus status
);

// Search Habits by Name
List<Habit> findByUserAndNameContainingIgnoreCase(
        User user,
        String keyword
);

// Status + Name + Pagination + Sorting
Page<Habit> findByUserAndStatusAndNameContainingIgnoreCase(
        User user,
        HabitStatus status,
        String keyword,
        Pageable pageable
);

    Optional<Habit> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, HabitStatus status);

}