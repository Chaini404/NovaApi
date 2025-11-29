package com.cibertec.ApiNova.contact.dtos.response;

public record ContactResponse(
        Long id,
        Long userId,
        String fullName,
        String phoneNumber,
        String email,
        Boolean enableWhatsapp
) {}
