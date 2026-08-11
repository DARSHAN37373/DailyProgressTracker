package com.darshan.dailyprogress.repository;


import com.darshan.dailyprogress.entity.Goal;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.darshan.dailyprogress.entity.GoalStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByUser(User user);

    Page<Goal> findByUser(User user, Pageable pageable);

    List<Goal> findByUserAndStatus(User user, GoalStatus status);

    List<Goal> findByUserAndTitleContainingIgnoreCase(
        User user,
        String keyword
);

    Page<Goal> findByUserAndStatusAndTitleContainingIgnoreCase(
        User user,
        GoalStatus status,
        String keyword,
        Pageable pageable
);

    Optional<Goal> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, GoalStatus status);
}