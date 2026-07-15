package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.DailyActivity;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DailyActivityRepository extends JpaRepository<DailyActivity, Long> {

    List<DailyActivity> findByUser(User user);

    Optional<DailyActivity> findByIdAndUser(Long id, User user);

    long countByUser(User user);
}