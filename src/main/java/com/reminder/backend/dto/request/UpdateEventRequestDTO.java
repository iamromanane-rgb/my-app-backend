package com.reminder.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for updating an existing event
 * All fields are optional to support partial updates
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequestDTO {
    
    private String eventType;
    
    private LocalDate eventDate;
    
    private String description;
}
