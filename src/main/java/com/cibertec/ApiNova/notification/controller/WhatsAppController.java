package com.cibertec.ApiNova.notification.controller;

import com.cibertec.ApiNova.notification.dto.EmergencyBroadcastRequest;
import com.cibertec.ApiNova.notification.service.WhatsAppService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class WhatsAppController {

    private final WhatsAppService whatsAppService;

    @Operation(summary = "Send emergency WhatsApp to multiple contacts")
    @PostMapping("/broadcast")
    public ResponseEntity<String> sendBroadcast(@RequestBody EmergencyBroadcastRequest request) {
        
        if (request.getPhoneNumbers() == null || request.getPhoneNumbers().isEmpty()) {
            return ResponseEntity.badRequest().body("No hay contactos seleccionados.");
        }

        // Ejecutar el servicio
        whatsAppService.sendEmergencyBroadcast(request);

        return ResponseEntity.ok("Alerta enviada a " + request.getPhoneNumbers().size() + " contactos.");
    }
}