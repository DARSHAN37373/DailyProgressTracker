package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.PlannerTask;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlannerTaskRepository extends JpaRepository<PlannerTask, Long> {

    List<PlannerTask> findByUser(User user);

    Optional<PlannerTask> findByIdAndUser(Long id, User user);

}