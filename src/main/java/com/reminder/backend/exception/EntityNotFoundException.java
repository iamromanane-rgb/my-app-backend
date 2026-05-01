package com.reminder.backend.exception;

/**
 * Custom exception for entity not found scenarios.
 * Used when a requested resource does not exist in the database.
 */
public class EntityNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Factory method to create exception with standardized message
     */
    public static EntityNotFoundException forEntity(String entityType, String identifier) {
        return new EntityNotFoundException(
            String.format("%s with identifier '%s' not found", entityType, identifier)
        );
    }

    /**
     * Factory method for ID-based lookups
     */
    public static EntityNotFoundException forId(String entityType, Long id) {
        return new EntityNotFoundException(
            String.format("%s with ID %d not found", entityType, id)
        );
    }
}
