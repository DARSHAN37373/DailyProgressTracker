package com.darshan.dailyprogress.repository;

import com.darshan.dailyprogress.entity.ActivityStatus;
import com.darshan.dailyprogress.entity.DailyActivity;
import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.darshan.dailyprogress.entity.ActivityStatus;

public interface DailyActivityRepository extends JpaRepository<DailyActivity, Long> {

    List<DailyActivity> findByUser(User user);

    List<DailyActivity> findByUserAndStatusOrderByActivityDateAsc(
        User user,
        ActivityStatus status);


    Optional<DailyActivity> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndActivityDateBetween(
        User user,
        LocalDate startDate,
        LocalDate endDate);
}