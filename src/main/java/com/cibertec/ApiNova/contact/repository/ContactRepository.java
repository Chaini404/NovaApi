package com.cibertec.ApiNova.contact.repository;

import com.cibertec.ApiNova.contact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Buscar contactos por usuario
    List<Contact> findByUserId(Long userId);

    // Opcional: buscar contactos por correo electrónico
    List<Contact> findByEmail(String email);
}
