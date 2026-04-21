package com.reminder.backend.admin;

import com.reminder.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminAccessService {

    private final Set<String> adminEmails;
    private final UserRepository userRepository;

    public AdminAccessService(
            @Value("${admin.emails:}") String adminEmails,
            UserRepository userRepository
    ) {
        this.adminEmails = Arrays.stream(adminEmails.split(","))
                .map(String::trim)
                .map(value -> value.toLowerCase(Locale.ROOT))
                .filter(value -> !value.isEmpty())
                .collect(Collectors.toSet());
        this.userRepository = userRepository;
    }

    /**
     * Check if user is admin by checking:
     * 1. Hardcoded admin emails from application.properties
     * 2. isAdmin flag in database
     */
    public boolean isAdmin(String email) {
        if (email == null) {
            return false;
        }
        
        String normalizedEmail = email.trim().toLowerCase(Locale.ROOT);
        
        // Check hardcoded admin list
        if (adminEmails.contains(normalizedEmail)) {
            return true;
        }
        
        // Check database isAdmin flag
        return userRepository.findByEmail(normalizedEmail)
                .map(user -> user.getIsAdmin() != null && user.getIsAdmin())
                .orElse(false);
    }
}
