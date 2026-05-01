package com.reminder.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for updating user information (for users updating their own profile)
 * All fields are optional to support partial updates
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {
    
    private String email;
    
    private String username;
    
    private String password;
}
