package com.cibertec.ApiNova.emergencyLocation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cibertec.ApiNova.emergencyLocation.dtos.request.CreateEmergencyLocationRequest;
import com.cibertec.ApiNova.emergencyLocation.dtos.response.EmergencyLocationResponse;
import com.cibertec.ApiNova.emergencyLocation.model.EmergencyLocation;

@Mapper(componentModel = "spring")
public interface EmergencyLocationMapper {

    EmergencyLocationMapper INSTANCE = Mappers.getMapper(EmergencyLocationMapper.class);

    EmergencyLocationResponse toResponse(EmergencyLocation emergencyLocation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "capturedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "emergencyEvent", ignore = true) // se seteará en el service
    EmergencyLocation toEntity(CreateEmergencyLocationRequest request);
}
