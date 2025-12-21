package com.cibertec.ApiNova.emergencyEvent.dtos.response;

import java.time.LocalDateTime;

import com.cibertec.ApiNova.emergencyEvent.model.type.EmergencyEventStatus;


public record EmergencyEventResponse(
        Long id,
        Long userId,

        EmergencyEventStatus status,
        LocalDateTime activatedAt,
        LocalDateTime closedAt
) {}
