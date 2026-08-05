package com.darshan.dailyprogress.reminder;

import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUser(User user);

    List<Reminder> findByUserAndStatus(User user, ReminderStatus status);

    // Search Reminders by Title
List<Reminder> findByUserAndTitleContainingIgnoreCase(
        User user,
        String keyword
);

// Status + Title + Pagination + Sorting
Page<Reminder> findByUserAndStatusAndTitleContainingIgnoreCase(
        User user,
        ReminderStatus status,
        String keyword,
        Pageable pageable
);

    Page<Reminder> findByUser(User user, Pageable pageable);

    List<Reminder> findByReminderDateAndReminderTimeLessThanEqualAndStatus(
        LocalDate reminderDate,
        LocalTime reminderTime,
        ReminderStatus status
);
}