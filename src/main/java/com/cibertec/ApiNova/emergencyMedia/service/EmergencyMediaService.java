package com.cibertec.ApiNova.emergencyMedia.service;

import com.cibertec.ApiNova.emergencyEvent.model.EmergencyEvent;
import com.cibertec.ApiNova.emergencyEvent.repository.EmergencyEventRepository;
import com.cibertec.ApiNova.emergencyMedia.dtos.request.CreateEmergencyMediaRequest;
import com.cibertec.ApiNova.emergencyMedia.dtos.response.EmergencyMediaResponse;
import com.cibertec.ApiNova.emergencyMedia.mapper.EmergencyMediaMapper;
import com.cibertec.ApiNova.emergencyMedia.model.EmergencyMedia;
import com.cibertec.ApiNova.emergencyMedia.repository.EmergencyMediaRepository;
import com.cibertec.ApiNova.emergencyLocation.model.EmergencyLocation;
import com.cibertec.ApiNova.emergencyLocation.repository.EmergencyLocationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmergencyMediaService {

    private final EmergencyMediaRepository emergencyMediaRepository;
    private final EmergencyMediaMapper emergencyMediaMapper;
    private final EmergencyEventRepository emergencyEventRepository;
    private final EmergencyLocationRepository emergencyLocationRepository;

   
    @Transactional
    public EmergencyMediaResponse createMedia(CreateEmergencyMediaRequest request) {
        EmergencyMedia media = emergencyMediaMapper.toEntity(request);
        // Asociar evento requerido (FK NOT NULL)
        EmergencyEvent event = emergencyEventRepository.findById(request.emergencyEventId())
                .orElseThrow(() -> new RuntimeException("EmergencyEvent not found: " + request.emergencyEventId()));
        media.setEmergencyEvent(event);

        // Asociar ubicación si viene
        if (request.emergencyLocationId() != null) {
            EmergencyLocation location = emergencyLocationRepository.findById(request.emergencyLocationId())
                    .orElseThrow(() -> new RuntimeException("EmergencyLocation not found: " + request.emergencyLocationId()));
            media.setEmergencyLocation(location);
        }

        EmergencyMedia savedMedia = emergencyMediaRepository.save(media);
        return emergencyMediaMapper.toResponse(savedMedia);
    }

    // =============================================================
    // UPDATE MEDIA
    // =============================================================
    /*
    @Transactional
    public EmergencyMediaResponse updateMedia(Long mediaId, UpdateEmergencyMediaRequest request) {
        EmergencyMedia media = emergencyMediaRepository.findById(mediaId)
            // Asociar evento requerido (FK NOT NULL)
            EmergencyEvent event = emergencyEventRepository.findById(request.emergencyEventId())
                    .orElseThrow(() -> new RuntimeException("EmergencyEvent not found: " + request.emergencyEventId()));
            media.setEmergencyEvent(event);
                .orElseThrow(() -> new RuntimeException("Media not found"));

        media.setUrl(request.url());
        media.setMediaType(request.mediaType());
        media.setDescription(request.description());

        EmergencyMedia updated = emergencyMediaRepository.save(media);
        return emergencyMediaMapper.toResponse(updated);
    }
    */
   
    @Transactional
    public EmergencyMediaResponse getMediaById(Long mediaId) {
        EmergencyMedia media = emergencyMediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        return emergencyMediaMapper.toResponse(media);
    }

  
    @Transactional
    public List<EmergencyMediaResponse> getMediaByEvent(Long eventId) {
        return emergencyMediaRepository.findByEmergencyEventId(eventId)
                .stream()
                .map(emergencyMediaMapper::toResponse)
                .collect(Collectors.toList());
    }

 
    @Transactional
    public List<EmergencyMediaResponse> getMediaByType(String mediaType) {
        return emergencyMediaRepository.findByMediaType(mediaType)
                .stream()
                .map(emergencyMediaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<EmergencyMediaResponse> getMediaByLocation(Long locationId) {
        return emergencyMediaRepository.findByEmergencyLocationId(locationId)
                .stream()
                .map(emergencyMediaMapper::toResponse)
                .collect(Collectors.toList());
    }


 
    @Transactional
    public void deleteMedia(Long mediaId) {
        EmergencyMedia media = emergencyMediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        emergencyMediaRepository.delete(media);
    }
}
