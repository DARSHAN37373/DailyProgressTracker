package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.Habit;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.darshan.dailyprogress.entity.HabitStatus;

import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findByUser(User user);

    Optional<Habit> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, HabitStatus status);

}