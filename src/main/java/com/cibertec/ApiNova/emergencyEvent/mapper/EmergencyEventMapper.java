package com.cibertec.ApiNova.emergencyEvent.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.ApiNova.emergencyEvent.dtos.request.CreateEmergencyEventRequest;
import com.cibertec.ApiNova.emergencyEvent.dtos.response.EmergencyEventResponse;
import com.cibertec.ApiNova.emergencyEvent.model.EmergencyEvent;

@Component
public class EmergencyEventMapper {

    // Convierte entidad a response
    public EmergencyEventResponse toResponse(EmergencyEvent event) {
        Long userId = event.getUser() != null ? event.getUser().getId() : null;

        return new EmergencyEventResponse(
            event.getId(),
            userId,
            event.getStatus(),
            event.getActivatedAt(),
            event.getClosedAt()
        );
    }

    // Convierte request a entidad
    public EmergencyEvent toEntity(CreateEmergencyEventRequest request) {
        EmergencyEvent event = new EmergencyEvent();
        event.setStatus(request.status()); // asignar status desde request
        // NOTA: User se asigna en el service, no aquí
        return event;
    }
}
