package com.cibertec.ApiNova.user.dtos.response;

import com.cibertec.ApiNova.user.model.type.UserStatus;

public record UserResponse(
    Long id,
    String fullName,
    String email,
    String phone,
    UserStatus status
) {}