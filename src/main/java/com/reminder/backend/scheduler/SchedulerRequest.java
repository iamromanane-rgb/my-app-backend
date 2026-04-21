package com.reminder.backend.scheduler;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SchedulerRequest { //for admin
    @NotBlank
    private String cron;

    public String getCron() { return cron; }
    public void setCron(String cron) { this.cron = cron; }
}
