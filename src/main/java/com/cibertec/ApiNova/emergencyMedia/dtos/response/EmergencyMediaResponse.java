package com.cibertec.ApiNova.emergencyMedia.dtos.response;

public record EmergencyMediaResponse(

    Long id,
    Long emergencyEventId,
    String mediaType,
    String storageUrl
    
) {
    
}
