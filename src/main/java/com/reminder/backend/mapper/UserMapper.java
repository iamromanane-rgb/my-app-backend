package com.reminder.backend.mapper;

import com.reminder.backend.dto.response.UserResponseDTO;
import com.reminder.backend.dto.request.UpdateUserRequestDTO;
import com.reminder.backend.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * MapStruct mapper for User entity <-> DTO conversions
 * Automatically generates implementation at compile time
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    /**
     * Convert User entity to UserResponseDTO
     * Excludes sensitive fields like passwordHash
     */
    UserResponseDTO toResponseDTO(User user);

    /**
     * Convert multiple User entities to UserResponseDTOs
     */
    java.util.List<UserResponseDTO> toResponseDTOList(java.util.List<User> users);

    /**
     * Update existing User entity with data from UpdateUserRequestDTO
     * Only non-null fields from DTO are applied to the entity
     */
    void updateUserFromDTO(UpdateUserRequestDTO dto, @MappingTarget User user);
}
