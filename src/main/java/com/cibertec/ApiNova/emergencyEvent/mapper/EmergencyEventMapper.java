package com.cibertec.ApiNova.emergencyEvent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cibertec.ApiNova.emergencyEvent.dtos.request.CreateEmergencyEventRequest;
import com.cibertec.ApiNova.emergencyEvent.dtos.response.EmergencyEventResponse;
import com.cibertec.ApiNova.emergencyEvent.model.EmergencyEvent;

@Mapper(componentModel = "spring")
public interface EmergencyEventMapper {
    EmergencyEventMapper INSTANCE = Mappers.getMapper(EmergencyEventMapper.class);

    EmergencyEventResponse toResponse(EmergencyEvent emergencyEvent);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true) // se asigna en el service
    EmergencyEvent toEntity(CreateEmergencyEventRequest request);

}
