package com.darshan.dailyprogress.reminder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderResponseDTO createReminder(
            @RequestBody ReminderRequestDTO request) {

        return reminderService.createReminder(request);
    }

    @GetMapping
    public List<ReminderResponseDTO> getAllReminders() {

        return reminderService.getAllReminders();
    }

    @GetMapping("/{id}")
    public ReminderResponseDTO getReminderById(
            @PathVariable Long id) {

        return reminderService.getReminderById(id);
    }

    @PutMapping("/{id}")
    public ReminderResponseDTO updateReminder(
            @PathVariable Long id,
            @RequestBody ReminderRequestDTO request) {

        return reminderService.updateReminder(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReminder(
            @PathVariable Long id) {

        reminderService.deleteReminder(id);
    }
}