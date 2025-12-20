package com.cibertec.ApiNova.contact.controller;

import com.cibertec.ApiNova.contact.dtos.request.CreateContactRequest;
import com.cibertec.ApiNova.contact.dtos.response.ContactResponse;
import com.cibertec.ApiNova.contact.service.ContactService;
import com.cibertec.ApiNova.user.model.User;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @Operation(summary = "Create a new contact")
    @PostMapping
    public ResponseEntity<ContactResponse> createContact(@RequestBody CreateContactRequest request) {
        ContactResponse response = contactService.createContact(request);
        return ResponseEntity.ok(response);
    }

    /*
    @Operation(summary = "Update contact by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ContactResponse> updateContact(
            @PathVariable Long id,
            @RequestBody UpdateContactRequest request) {

        ContactResponse response = contactService.updateContact(id, request);
        return ResponseEntity.ok(response);
    }
    */

    // inicio - sobre twilio

    @PostMapping("/emergency/alert")
public String sendEmergencyAlert(@RequestParam String location) {
    // ⚠️ Simulación: usuario demo, luego usar Spring Security
    User user = getAuthenticatedUser();

    contactService.sendEmergencyWhatsApp(user, location);

    return "Alerta WhatsApp enviada correctamente al demo";
}

private User getAuthenticatedUser() {
    User user = new User();
    user.setId(1L);
    user.setFullName("Usuario Demo");
    user.setEmail("demo@email.com");
    return user;
}

    // fin - sobre twilio

    @Operation(summary = "Get contact by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponse> getContactById(@PathVariable Long id) {
        ContactResponse response = contactService.getContactById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all contacts")
    @GetMapping
    public ResponseEntity<List<ContactResponse>> getAllContacts() {
        List<ContactResponse> list = contactService.getAllContacts();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get contacts by User ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ContactResponse>> getContactsByUser(@PathVariable Long userId) {
        List<ContactResponse> list = contactService.getContactsByUser(userId);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Delete a contact")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
