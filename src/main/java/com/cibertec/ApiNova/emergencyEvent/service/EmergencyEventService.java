package com.cibertec.ApiNova.emergencyEvent.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cibertec.ApiNova.emergencyEvent.dtos.request.CreateEmergencyEventRequest;
import com.cibertec.ApiNova.emergencyEvent.dtos.response.EmergencyEventResponse;
import com.cibertec.ApiNova.emergencyEvent.mapper.EmergencyEventMapper;
import com.cibertec.ApiNova.emergencyEvent.model.EmergencyEvent;
import com.cibertec.ApiNova.emergencyEvent.repository.EmergencyEventRepository;
import com.cibertec.ApiNova.user.model.User;
import com.cibertec.ApiNova.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmergencyEventService {

    private final EmergencyEventRepository emergencyEventRepository;
    private final EmergencyEventMapper emergencyEventMapper;
    private final UserRepository userRepository;

    @Transactional
    public EmergencyEventResponse createEvent(CreateEmergencyEventRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        EmergencyEvent event = emergencyEventMapper.toEntity(request);
        event.setUser(user);

        EmergencyEvent savedEvent = emergencyEventRepository.save(event);
        return emergencyEventMapper.toResponse(savedEvent);
    }

    @Transactional
    public EmergencyEventResponse getEventById(Long id) {
        EmergencyEvent event = emergencyEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return emergencyEventMapper.toResponse(event);
    }

    @Transactional
    public List<EmergencyEventResponse> getEventsByUser(Long userId) {
        return emergencyEventRepository.findByUserId(userId)
                .stream()
                .map(emergencyEventMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<EmergencyEventResponse> getAllEvents() {
        return emergencyEventRepository.findAll()
                .stream()
                .map(emergencyEventMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmergencyEventResponse closeEvent(Long id) {
        EmergencyEvent event = emergencyEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        
        event.setClosedAt(java.time.LocalDateTime.now());

        EmergencyEvent updated = emergencyEventRepository.save(event);
        return emergencyEventMapper.toResponse(updated);
    }

    @Transactional
public List<EmergencyEventResponse> getActiveEvents() {
    return emergencyEventRepository.findByResolvedFalse()
            .stream()
            .map(emergencyEventMapper::toResponse)
            .collect(Collectors.toList());
}

@Transactional
public List<EmergencyEventResponse> getResolvedEvents() {
    return emergencyEventRepository.findByResolvedTrue()
            .stream()
            .map(emergencyEventMapper::toResponse)
            .collect(Collectors.toList());
}


}
