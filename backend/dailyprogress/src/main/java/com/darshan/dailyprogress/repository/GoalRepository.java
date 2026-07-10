package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}