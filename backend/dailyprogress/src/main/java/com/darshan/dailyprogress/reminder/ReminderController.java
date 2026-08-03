package com.darshan.dailyprogress.reminder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;

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
        @Valid @RequestBody ReminderRequestDTO request) {

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

    // Get Reminders with Pagination and Sorting
@GetMapping("/page")
public Page<ReminderResponseDTO> getRemindersPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "reminderDate") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    return reminderService.getRemindersPaginated(
            page,
            size,
            sortBy,
            direction
    );
}

    @PutMapping("/{id}")
public ReminderResponseDTO updateReminder(
        @PathVariable Long id,
        @Valid @RequestBody ReminderRequestDTO request) {

    return reminderService.updateReminder(id, request);
}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReminder( @PathVariable Long id) {

        reminderService.deleteReminder(id);
    }
}