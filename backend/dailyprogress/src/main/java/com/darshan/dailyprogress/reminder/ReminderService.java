package com.darshan.dailyprogress.reminder;

import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.service.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final CurrentUserService currentUserService;

    public ReminderService(ReminderRepository reminderRepository,
                           CurrentUserService currentUserService) {

        this.reminderRepository = reminderRepository;
        this.currentUserService = currentUserService;
    }

    // Create Reminder
    public ReminderResponseDTO createReminder(ReminderRequestDTO request) {

        User user = currentUserService.getCurrentUser();

        Reminder reminder = new Reminder();

        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderDate(request.getReminderDate());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setRepeatType(request.getRepeatType());
        reminder.setUser(user);

        reminder = reminderRepository.save(reminder);

        return mapToResponse(reminder);
    }

    // Get All Reminders
    public List<ReminderResponseDTO> getAllReminders() {

        User user = currentUserService.getCurrentUser();

        return reminderRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get Reminder By Id
    public ReminderResponseDTO getReminderById(Long id) {

        User user = currentUserService.getCurrentUser();

        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        if (!reminder.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        return mapToResponse(reminder);
    }

    // Update Reminder
    public ReminderResponseDTO updateReminder(Long id,
                                              ReminderRequestDTO request) {

        User user = currentUserService.getCurrentUser();

        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        if (!reminder.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderDate(request.getReminderDate());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setRepeatType(request.getRepeatType());

        reminder = reminderRepository.save(reminder);

        return mapToResponse(reminder);
    }

    // Delete Reminder
    public void deleteReminder(Long id) {

        User user = currentUserService.getCurrentUser();

        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        if (!reminder.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        reminderRepository.delete(reminder);
    }

    // Convert Entity → DTO
    private ReminderResponseDTO mapToResponse(Reminder reminder) {

        ReminderResponseDTO response = new ReminderResponseDTO();

        response.setId(reminder.getId());
        response.setTitle(reminder.getTitle());
        response.setDescription(reminder.getDescription());
        response.setReminderDate(reminder.getReminderDate());
        response.setReminderTime(reminder.getReminderTime());
        response.setRepeatType(reminder.getRepeatType());
        response.setStatus(reminder.getStatus());

        return response;
    }
}