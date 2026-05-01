package com.reminder.backend.mapper;

import com.reminder.backend.admin.AdminPromotionRequest;
import com.reminder.backend.admin.AdminUserUpdateRequest;
import com.reminder.backend.dto.response.UserResponseDTO;
import com.reminder.backend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * MapStruct mapper for Admin-specific User operations
 * Handles admin-only update operations and conversions
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdminUserMapper {

    /**
     * Convert User entity to UserResponseDTO
     */
    UserResponseDTO toResponseDTO(User user);

    /**
     * Update User entity with admin update request data
     * Allows admins to update email, username, empId, and accessLevel
     */
    void updateUserFromAdminRequest(AdminUserUpdateRequest dto, @MappingTarget User user);

    /**
     * Update User entity with promotion request data
     * Only updates the isAdmin flag
     */
    void updateUserFromPromotionRequest(AdminPromotionRequest dto, @MappingTarget User user);
}
