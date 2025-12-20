package com.cibertec.ApiNova.contact.repository;

import com.cibertec.ApiNova.contact.model.Contact;
import com.cibertec.ApiNova.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Buscar contactos por usuario
    List<Contact> findByUserId(Long userId);

    // Opcional: buscar contactos por correo electrónico
    List<Contact> findByEmail(String email);

    //buscar el contacto de emergencia de un usuario
    Optional<Contact> findByUserAndEmergencyContactTrue(User user);

}
