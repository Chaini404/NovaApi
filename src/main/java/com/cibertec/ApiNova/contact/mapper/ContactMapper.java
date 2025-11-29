package com.cibertec.ApiNova.contact.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cibertec.ApiNova.contact.dtos.request.CreateContactRequest;
import com.cibertec.ApiNova.contact.dtos.response.ContactResponse;
import com.cibertec.ApiNova.contact.model.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactResponse toResponse(Contact contact);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Contact toEntity(CreateContactRequest request);
}
