package com.cibertec.ApiNova.user.mapper;

import com.cibertec.ApiNova.user.dtos.request.CreateUserRequest;
import com.cibertec.ApiNova.user.dtos.response.UserResponse;
import com.cibertec.ApiNova.user.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Convertir de entidad a response
    UserResponse toResponse(User user);

    // Convertir de CreateUserRequest a entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "ACTIVE") // por defecto al crear
    User toEntity(CreateUserRequest request);
}
