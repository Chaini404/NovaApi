package com.cibertec.ApiNova.emergencyMedia.dtos.response;

import com.cibertec.ApiNova.emergencyMedia.model.type.EmergencyMediaStatus;

public record EmergencyMediaResponse(

    Long id,
    Long emergencyEventId,
    EmergencyMediaStatus mediaType,
    String storageUrl
    
) {}
