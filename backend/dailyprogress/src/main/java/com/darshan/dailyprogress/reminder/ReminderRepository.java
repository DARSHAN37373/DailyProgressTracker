package com.darshan.dailyprogress.reminder;

import com.darshan.dailyprogress.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUser(User user);

    List<Reminder> findByUserAndStatus(User user, ReminderStatus status);

    List<Reminder> findByReminderDateAndReminderTimeLessThanEqualAndStatus(
        LocalDate reminderDate,
        LocalTime reminderTime,
        ReminderStatus status
);
}