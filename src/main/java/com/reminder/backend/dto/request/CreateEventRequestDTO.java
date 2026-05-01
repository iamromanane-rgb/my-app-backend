package com.reminder.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for creating a new event
 * Used in API requests when clients create events
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequestDTO {
    
    @NotBlank(message = "Event type is required")
    private String eventType;
    
    @NotBlank(message = "Event date is required")
    private String eventDate; // String format: YYYY-MM-DD
    
    private String description;
}
