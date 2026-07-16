package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.Goal;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.darshan.dailyprogress.entity.GoalStatus;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findByUser(User user);

    Optional<Goal> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, GoalStatus status);
}