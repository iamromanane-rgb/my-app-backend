package com.reminder.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Event response
 * Used for API responses when returning event data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponseDTO {
    private Long id;
    private String eventType;
    private LocalDate eventDate;
    private String description;
    private Long userId;
}
