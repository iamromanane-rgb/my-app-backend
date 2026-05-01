package com.reminder.backend.mapper;

import com.reminder.backend.dto.response.EventResponseDTO;
import com.reminder.backend.dto.request.CreateEventRequestDTO;
import com.reminder.backend.dto.request.UpdateEventRequestDTO;
import com.reminder.backend.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MapStruct mapper for Event entity <-> DTO conversions
 * Includes custom logic for date string parsing
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    /**
     * Convert Event entity to EventResponseDTO
     */
    @Mapping(target = "userId", source = "user.id")
    EventResponseDTO toResponseDTO(Event event);

    /**
     * Convert multiple Event entities to EventResponseDTOs
     */
    java.util.List<EventResponseDTO> toResponseDTOList(java.util.List<Event> events);

    /**
     * Convert CreateEventRequestDTO to Event entity
     * Custom mapping for eventDate string to LocalDate conversion
     */
    @Mapping(target = "eventDate", source = "eventDate")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Event toEntity(CreateEventRequestDTO dto);

    /**
     * Update existing Event entity with data from UpdateEventRequestDTO
     * Only non-null fields are applied
     */
    void updateEventFromDTO(UpdateEventRequestDTO dto, @MappingTarget Event event);

    /**
     * Convert date string (YYYY-MM-DD) to LocalDate
     * Custom method for handling date conversion
     */
    default LocalDate stringToLocalDate(String dateString) {
        if (dateString == null || dateString.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD format", e);
        }
    }

    /**
     * Convert LocalDate to date string
     */
    default String localDateToString(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
