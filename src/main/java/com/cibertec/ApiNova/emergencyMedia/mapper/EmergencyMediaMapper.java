package com.cibertec.ApiNova.emergencyMedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cibertec.ApiNova.emergencyMedia.dtos.request.CreateEmergencyMediaRequest;
import com.cibertec.ApiNova.emergencyMedia.dtos.response.EmergencyMediaResponse;
import com.cibertec.ApiNova.emergencyMedia.model.EmergencyMedia;

@Mapper(componentModel = "spring")
public interface EmergencyMediaMapper {

    EmergencyMediaMapper INSTANCE = Mappers.getMapper(EmergencyMediaMapper.class);

    EmergencyMediaResponse toResponse(EmergencyMedia emergencyMedia);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emergencyEvent", ignore = true) // se seteará en el service
    EmergencyMedia toEntity(CreateEmergencyMediaRequest request);
}
