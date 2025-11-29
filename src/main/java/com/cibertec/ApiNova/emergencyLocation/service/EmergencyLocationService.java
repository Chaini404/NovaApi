package com.cibertec.ApiNova.emergencyLocation.service;

import com.cibertec.ApiNova.emergencyLocation.dtos.request.CreateEmergencyLocationRequest;
import com.cibertec.ApiNova.emergencyLocation.dtos.response.EmergencyLocationResponse;
import com.cibertec.ApiNova.emergencyLocation.mapper.EmergencyLocationMapper;
import com.cibertec.ApiNova.emergencyLocation.model.EmergencyLocation;
import com.cibertec.ApiNova.emergencyLocation.repository.EmergencyLocationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmergencyLocationService {

    private final EmergencyLocationRepository emergencyLocationRepository;
    private final EmergencyLocationMapper emergencyLocationMapper;

  
    @Transactional
    public EmergencyLocationResponse createLocation(CreateEmergencyLocationRequest request) {
        EmergencyLocation location = emergencyLocationMapper.toEntity(request);
        EmergencyLocation saved = emergencyLocationRepository.save(location);
        return emergencyLocationMapper.toResponse(saved);
    }

    // ------------------------------------------------------------
    // UPDATE
    // ------------------------------------------------------------
    /* 
    @Transactional
    public EmergencyLocationResponse updateLocation(Long locationId, UpdateEmergencyLocationRequest request) {
        EmergencyLocation location = emergencyLocationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        location.setLatitude(request.latitude());
        location.setLongitude(request.longitude());
        location.setAddress(request.address());
        location.setDescription(request.description());

        EmergencyLocation updated = emergencyLocationRepository.save(location);
        return emergencyLocationMapper.toResponse(updated);
    }
    */
    @Transactional
    public EmergencyLocationResponse getLocationById(Long locationId) {
        EmergencyLocation location = emergencyLocationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return emergencyLocationMapper.toResponse(location);
    }

 
    @Transactional
    public List<EmergencyLocationResponse> getAllLocations() {
        return emergencyLocationRepository.findAll()
                .stream()
                .map(emergencyLocationMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<EmergencyLocationResponse> getLocationsByEvent(Long emergencyEventId) {
        return emergencyLocationRepository.findByEmergencyEventId(emergencyEventId)
                .stream()
                .map(emergencyLocationMapper::toResponse)
                .collect(Collectors.toList());
    }

    
    @Transactional
    public void deleteLocation(Long locationId) {
        if (!emergencyLocationRepository.existsById(locationId)) {
            throw new RuntimeException("Location not found");
        }
        emergencyLocationRepository.deleteById(locationId);
    }
}
