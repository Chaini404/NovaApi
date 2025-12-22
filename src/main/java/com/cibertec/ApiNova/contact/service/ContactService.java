package com.cibertec.ApiNova.contact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cibertec.ApiNova.contact.dtos.request.CreateContactRequest;
import com.cibertec.ApiNova.contact.dtos.response.ContactResponse;
import com.cibertec.ApiNova.contact.mapper.ContactMapper;
import com.cibertec.ApiNova.contact.model.Contact;
import com.cibertec.ApiNova.contact.repository.ContactRepository;
import com.cibertec.ApiNova.user.model.User;
import com.cibertec.ApiNova.user.repository.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final UserRepository userRepository;

    // inicio - sobre twilio
    @Value("${twilio.phoneNumber}")
    private String fromPhone;
    @Value("${twilio.defaultCountryCode:+51}")
    private String defaultCountryCode;

    
    public void sendEmergencyWhatsApp(User user, String location) {

        // Obtener todos los contactos de emergencia del usuario
        List<Contact> contacts = contactRepository.findByUserAndEmergencyContactTrue(user);

        // Filtrar solo contactos con WhatsApp habilitado
        contacts = contacts.stream()
            .filter(c -> Boolean.TRUE.equals(c.getEnableWhatsapp()))
            .toList();

        if (contacts.isEmpty()) {
            throw new RuntimeException("No hay contactos de emergencia configurados para el usuario");
        }

        String message = buildEmergencyMessage(user, location);

        for (Contact contact : contacts) {
            if (contact.getPhoneNumber() != null) {
            String to = "whatsapp:" + formatPhoneNumber(contact.getPhoneNumber());

                try {
                    Message msg = Message.creator(
                            new PhoneNumber(to),
                            new PhoneNumber(fromPhone),
                            message
                    ).create();

                    System.out.println("Mensaje WhatsApp enviado a " + contact.getPhoneNumber() + " SID: " + msg.getSid());
                } catch (Exception e) {
                    System.err.println("Error enviando a " + contact.getPhoneNumber() + ": " + e.getMessage());
                }
            }
        }
    }

    private String buildEmergencyMessage(User user, String location) {
        return "🚨 ALERTA DE EMERGENCIA 🚨\n\n"
                + "Usuario: " + user.getFullName() + "\n"
                + "Email: " + user.getEmail() + "\n"
                + "Ubicación: " + location + "\n"
                + "Hora: " + java.time.LocalDateTime.now();
    }

    private String formatPhoneNumber(String number) {
        if (number == null || number.isBlank()) return number;
        String trimmed = number.trim();
        boolean hadPlus = trimmed.startsWith("+");
        // Mantener solo dígitos
        String digits = trimmed.replaceAll("[^0-9]", "");
        String code = defaultCountryCode != null ? defaultCountryCode : "+51";
        String codeDigits = code.replaceAll("[^0-9]", "");

        if (digits.isEmpty()) return code + digits;

        // Si ya tenía + o ya incluye el código de país al inicio, solo asegurar el +
        if (hadPlus || (!codeDigits.isEmpty() && digits.startsWith(codeDigits))) {
            return "+" + digits;
        }
        // Agregar código por defecto
        return "+" + codeDigits + digits;
    }
    // fin - twilio
    @Transactional
    public ContactResponse createContact(CreateContactRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Contact contact = contactMapper.toEntity(request);
        contact.setUser(user);

        Contact saved = contactRepository.save(contact);
        return contactMapper.toResponse(saved);
    }

    @Transactional
    public List<ContactResponse> getContactsByUser(Long userId) {
        return contactRepository.findByUserId(userId)
                .stream()
                .map(contactMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contactRepository.delete(contact);
    }

    @Transactional
public ContactResponse getContactById(Long contactId) {
    Contact contact = contactRepository.findById(contactId)
            .orElseThrow(() -> new RuntimeException("Contact not found"));
    return contactMapper.toResponse(contact);
}

@Transactional
public List<ContactResponse> getAllContacts() {
    return contactRepository.findAll()
            .stream()
            .map(contactMapper::toResponse)
            .collect(Collectors.toList());
}

}
