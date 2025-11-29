package com.cibertec.ApiNova.emergencyEvent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.ApiNova.emergencyEvent.model.EmergencyEvent;

import java.util.List;

@Repository
public interface EmergencyEventRepository extends JpaRepository<EmergencyEvent, Long> {

    // Buscar eventos de emergencia por usuario
    List<EmergencyEvent> findByUserId(Long userId);

    // Buscar eventos activos (no resueltos)
    List<EmergencyEvent> findByResolvedFalse();

    // Buscar eventos cerrados
    List<EmergencyEvent> findByResolvedTrue();
}
