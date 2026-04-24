package com.reminder.backend.controllers;

import com.reminder.backend.models.Event;
import com.reminder.backend.repositories.EventRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upcoming")
@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "bearerAuth")
public class UpcomingEventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> getUpcomingEvents(
            @RequestParam(value = "days", defaultValue = "7") int days
    ) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(days);

        // Fetch all users' events sorted by upcoming date at database level
        List<Event> upcomingEvents = eventRepository.findUpcomingEvents(today);

        // Filter to only include events within the requested timeframe
        return upcomingEvents.stream()
                .filter(event -> {
                    LocalDate eventDate = event.getEventDate();
                    LocalDate currentYearDate = eventDate.withYear(today.getYear());

                    // Handle recurring yearly events that have already passed this year
                    if (currentYearDate.isBefore(today) || currentYearDate.equals(today.minusDays(1))) {
                        currentYearDate = currentYearDate.plusYears(1);
                    }

                    // Check if the adjusted date falls within the specified time range
                    return !currentYearDate.isBefore(today) && !currentYearDate.isAfter(endDate);
                })
                .collect(Collectors.toList());
    }
}
