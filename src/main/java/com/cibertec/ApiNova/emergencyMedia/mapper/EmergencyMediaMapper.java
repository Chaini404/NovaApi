package com.cibertec.ApiNova.emergencyMedia.mapper;

import org.springframework.stereotype.Component;

import com.cibertec.ApiNova.emergencyMedia.dtos.request.CreateEmergencyMediaRequest;
import com.cibertec.ApiNova.emergencyMedia.dtos.response.EmergencyMediaResponse;
import com.cibertec.ApiNova.emergencyMedia.model.EmergencyMedia;

@Component
public class EmergencyMediaMapper {

    // Convierte entidad a response
    public EmergencyMediaResponse toResponse(EmergencyMedia media) {
        Long emergencyEventId = media.getEmergencyEvent() != null ? media.getEmergencyEvent().getId() : null;
        Long emergencyLocationId = media.getEmergencyLocation() != null ? media.getEmergencyLocation().getId() : null;
        String createdAt = media.getCreatedAt() != null ? media.getCreatedAt().toString() : null;

        return new EmergencyMediaResponse(
            media.getId(),
            emergencyEventId,
            media.getMediaType(),
            media.getStorageUrl(),
            emergencyLocationId,
            createdAt
        );
    }

    // Convierte request a entidad
    public EmergencyMedia toEntity(CreateEmergencyMediaRequest request) {
        EmergencyMedia media = new EmergencyMedia();
        media.setMediaType(request.mediaType());
        media.setStorageUrl(request.storageUrl());
        media.setCreatedAt(java.time.LocalDateTime.now());
        // emergencyEvent se asigna en el service
        return media;
    }
}
