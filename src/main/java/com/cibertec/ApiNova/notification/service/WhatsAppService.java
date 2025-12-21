package com.cibertec.ApiNova.notification.service;

import com.cibertec.ApiNova.notification.dto.EmergencyBroadcastRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhatsAppService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber; 

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public void sendEmergencyBroadcast(EmergencyBroadcastRequest request) {
        List<String> contacts = request.getPhoneNumbers();
        
        String bodyMessage = "🚨 *ALERTA NOVA* 🚨\n" +
                "El usuario *" + request.getUserName() + "* ha activado el botón de pánico.\n" +
                "Ubicación en tiempo real: " + request.getGoogleMapsLink() + "\n" +
                "Por favor, verifique su estado.";

        String formattedFrom = "whatsapp:" + fromNumber;

        contacts.parallelStream().forEach(phoneNumber -> {
            try {
                String cleanNumber = phoneNumber.trim();
                if (!cleanNumber.startsWith("whatsapp:")) {
                    cleanNumber = "whatsapp:" + cleanNumber;
                }

                Message.creator(
                    new PhoneNumber(cleanNumber), // Destino
                    new PhoneNumber(formattedFrom), // Origen
                    bodyMessage                     // Mensaje
                ).create();

                System.out.println("✅ Mensaje enviado a: " + cleanNumber);

            } catch (Exception e) {
                System.err.println("❌ Error enviando a " + phoneNumber + ": " + e.getMessage());
            }
        });
    }
}