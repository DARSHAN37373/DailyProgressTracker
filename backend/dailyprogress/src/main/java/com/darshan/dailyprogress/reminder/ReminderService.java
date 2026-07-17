package com.darshan.dailyprogress.reminder;

import com.darshan.dailyprogress.entity.User;
import com.darshan.dailyprogress.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;

    public ReminderService(ReminderRepository reminderRepository,
                           UserRepository userRepository) {
        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
    }


private User getLoggedInUser() {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
}

public ReminderResponseDTO createReminder(ReminderRequestDTO request) {

    User user = getLoggedInUser();

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

public List<ReminderResponseDTO> getAllReminders() {

    User user = getLoggedInUser();

    return reminderRepository.findByUser(user)
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
}

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
public ReminderResponseDTO getReminderById(Long id) {

    User user = getLoggedInUser();

    Reminder reminder = reminderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reminder not found"));

    if (!reminder.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Access denied");
    }

    return mapToResponse(reminder);
}
public ReminderResponseDTO updateReminder(Long id,
                                          ReminderRequestDTO request) {

    User user = getLoggedInUser();

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
public void deleteReminder(Long id) {

    User user = getLoggedInUser();

    Reminder reminder = reminderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reminder not found"));

    if (!reminder.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Access denied");
    }

    reminderRepository.delete(reminder);
}
}