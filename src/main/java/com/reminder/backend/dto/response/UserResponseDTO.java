package com.reminder.backend.dto.response;

import com.reminder.backend.models.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for User response - excludes sensitive fields like passwordHash
 * Used for API responses when returning user data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String username;
    private Long empId;
    private AccessLevel accessLevel;
    private Boolean isAdmin;
    private LocalDateTime createdAt;
}
