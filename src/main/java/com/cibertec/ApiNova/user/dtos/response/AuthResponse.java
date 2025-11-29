package com.cibertec.ApiNova.user.dtos.response;

public record AuthResponse(String token, Long userId, String email) {}
