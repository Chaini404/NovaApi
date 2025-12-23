package com.cibertec.ApiNova.emergencyMedia.dtos.request;

import com.cibertec.ApiNova.emergencyMedia.model.type.EmergencyMediaStatus;

import io.swagger.v3.oas.models.media.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreateEmergencyMediaRequest(

        @NotNull(message = "Emergency Event ID is required")
        Long emergencyEventId,

        @NotNull(message = "Media type is required")
        EmergencyMediaStatus mediaType,

        @NotBlank(message = "Storage URL is required")
        @Size(max = 255, message = "Storage URL cannot exceed 255 characters")
        String storageUrl,

        // Optional: link media to a specific location in the route
        Long emergencyLocationId
) {}
