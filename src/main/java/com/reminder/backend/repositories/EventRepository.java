package com.reminder.backend.repositories;

import com.reminder.backend.models.Event;

import jakarta.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByUserId(Long userId);

    Optional<Event> findByIdAndUserId(Long id, Long userId);

    List<Event> findByUserIdAndEventDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Event> findByUserIdAndDescriptionContainingIgnoreCase(Long userId, String keyword);
    
    @Query("SELECT e FROM Event e WHERE EXTRACT(MONTH FROM e.eventDate) = :month AND EXTRACT(DAY FROM e.eventDate) = :day")
    List<Event> findByMonthAndDay(@Param("month") int month, @Param("day") int day);
    
    // Optimized query for upcoming events - filters at database level for performance
    @Query("""
        SELECT e FROM Event e 
        ORDER BY 
            CASE 
                WHEN MONTH(CAST(e.eventDate AS date)) > MONTH(CAST(:today AS date)) 
                     OR (MONTH(CAST(e.eventDate AS date)) = MONTH(CAST(:today AS date)) AND DAY(CAST(e.eventDate AS date)) >= DAY(CAST(:today AS date))) 
                THEN CONCAT(YEAR(CAST(:today AS date)), '-', LPAD(CAST(MONTH(CAST(e.eventDate AS date)) AS text), 2, '0'), '-', LPAD(CAST(DAY(CAST(e.eventDate AS date)) AS text), 2, '0'))
                ELSE CONCAT(YEAR(CAST(:today AS date)) + 1, '-', LPAD(CAST(MONTH(CAST(e.eventDate AS date)) AS text), 2, '0'), '-', LPAD(CAST(DAY(CAST(e.eventDate AS date)) AS text), 2, '0'))
            END ASC
    """)
    List<Event> findUpcomingEvents(@Param("today") LocalDate today);
}