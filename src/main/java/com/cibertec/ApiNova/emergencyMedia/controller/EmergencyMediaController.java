package com.cibertec.ApiNova.emergencyMedia.controller;

import com.cibertec.ApiNova.emergencyMedia.dtos.request.CreateEmergencyMediaRequest;
import com.cibertec.ApiNova.emergencyMedia.dtos.response.EmergencyMediaResponse;
import com.cibertec.ApiNova.emergencyMedia.service.EmergencyMediaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/api/emergency-media")
@RequiredArgsConstructor
public class EmergencyMediaController {

    private final EmergencyMediaService emergencyMediaService;
    @Value("${uploads.dir:uploads}")
    private String uploadsDir;


    @Operation(summary = "Create a new emergency media record")
    @PostMapping
        public ResponseEntity<EmergencyMediaResponse> createMedia(
            @Valid @RequestBody CreateEmergencyMediaRequest request) {

        EmergencyMediaResponse response = emergencyMediaService.createMedia(request);
        return ResponseEntity.ok(response);
    }

 
    @Operation(summary = "Get media by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmergencyMediaResponse> getMediaById(@PathVariable Long id) {
        EmergencyMediaResponse response = emergencyMediaService.getMediaById(id);
        return ResponseEntity.ok(response);
    }

   
    @Operation(summary = "Get all media related to an emergency event")
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EmergencyMediaResponse>> getMediaByEvent(@PathVariable Long eventId) {
        List<EmergencyMediaResponse> response = emergencyMediaService.getMediaByEvent(eventId);
        return ResponseEntity.ok(response);
    }

 
    @Operation(summary = "Get media by type (PHOTO, VIDEO, AUDIO)")
    @GetMapping("/type/{mediaType}")
    public ResponseEntity<List<EmergencyMediaResponse>> getMediaByType(@PathVariable String mediaType) {
        List<EmergencyMediaResponse> response = emergencyMediaService.getMediaByType(mediaType);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get media by route location ID")
    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<EmergencyMediaResponse>> getMediaByLocation(@PathVariable Long locationId) {
        List<EmergencyMediaResponse> response = emergencyMediaService.getMediaByLocation(locationId);
        return ResponseEntity.ok(response);
    }

    // =============================================================
    // UPDATE
    // =============================================================
    /*
    @Operation(summary = "Update media by ID")
    @PutMapping("/{id}")
    public ResponseEntity<EmergencyMediaResponse> updateMedia(
            @PathVariable Long id,
            @RequestBody UpdateEmergencyMediaRequest request) {

        EmergencyMediaResponse response = emergencyMediaService.updateMedia(id, request);
        return ResponseEntity.ok(response);
    }
         */

 
    @Operation(summary = "Delete media by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        emergencyMediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Upload raw media file and return public URL")
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        // Ensure upload dir exists
        Path base = Paths.get(uploadsDir).toAbsolutePath();
        Files.createDirectories(base);

        String original = file.getOriginalFilename();
        String ext = (original != null && original.contains(".")) ? original.substring(original.lastIndexOf('.')) : ".bin";
        String name = UUID.randomUUID().toString() + ext;
        Path target = base.resolve(name);
        Files.write(target, file.getBytes());

        // Public URL served by WebMvc resource handler at /uploads/**
        String publicUrl = "/uploads/" + name;
        return ResponseEntity.ok(Map.of("url", publicUrl));
    }
}
