package com.darshan.dailyprogress.reminder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class ReminderScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(ReminderScheduler.class);

    private final ReminderRepository reminderRepository;

    public ReminderScheduler(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void checkReminders() {

        LocalDate today = LocalDate.now();

        LocalTime now = LocalTime.now().withSecond(0).withNano(0);

        log.info("Scheduler Today: {}", today);
        log.info("Scheduler Time : {}", now);

        List<Reminder> reminders =
                reminderRepository.findByReminderDateAndReminderTimeLessThanEqualAndStatus(
        today,
        now,
        
        ReminderStatus.PENDING
);
         log.info("Reminders Found: {}", reminders.size());

        if (reminders.isEmpty()) {
            log.info("No reminders due.");
            return;
        }

        for (Reminder reminder : reminders) {
log.info(
    "Reminder Due - Title: {}, Date: {}, Time: {}",
    reminder.getTitle(),
    reminder.getReminderDate(),
    reminder.getReminderTime()
);
log.info("🔔 Reminder Due!");
log.info("Title: {}", reminder.getTitle());
log.info("Description: {}", reminder.getDescription());
log.info("Date: {}", reminder.getReminderDate());
log.info("Time: {}", reminder.getReminderTime());
log.info("============================");

    switch (reminder.getRepeatType()) {

    case NONE:
        reminder.setStatus(ReminderStatus.COMPLETED);
        log.info("One-time reminder marked as COMPLETED.");
        break;

    case DAILY:
        reminder.setReminderDate(
                reminder.getReminderDate().plusDays(1));
        log.info("Daily reminder rescheduled.");
        break;

    case WEEKLY:
        reminder.setReminderDate(
                reminder.getReminderDate().plusWeeks(1));
        log.info("Weekly reminder rescheduled.");
        break;

    case MONTHLY:
        reminder.setReminderDate(
                reminder.getReminderDate().plusMonths(1));
        log.info("Monthly reminder rescheduled.");
        break;

    

    case YEARLY:
        reminder.setReminderDate(
                reminder.getReminderDate().plusYears(1));
        log.info("Yearly reminder rescheduled.");
        break;
}

reminderRepository.save(reminder);
}
    }
}