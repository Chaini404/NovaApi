package com.cibertec.ApiNova.emergencyEvent.dtos.request;

import com.cibertec.ApiNova.emergencyEvent.model.type.EmergencyEventStatus;

import jakarta.validation.constraints.NotNull;

public record CreateEmergencyEventRequest(

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Event type is required")
        EmergencyEventStatus eventType
) {}
