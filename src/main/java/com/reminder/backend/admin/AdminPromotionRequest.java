package com.reminder.backend.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class AdminPromotionRequest {
    
    @JsonProperty("isAdmin")
    @NotNull(message = "isAdmin field is required")
    private Boolean isAdmin;

    public AdminPromotionRequest() {}

    public AdminPromotionRequest(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
