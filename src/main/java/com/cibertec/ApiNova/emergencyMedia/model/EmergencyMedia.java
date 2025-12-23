package com.cibertec.ApiNova.emergencyMedia.model;

import com.cibertec.ApiNova.emergencyEvent.model.EmergencyEvent;
import com.cibertec.ApiNova.emergencyLocation.model.EmergencyLocation;
import com.cibertec.ApiNova.emergencyMedia.model.type.EmergencyMediaStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "emergency_media")
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_event_id", nullable = false)
    private EmergencyEvent emergencyEvent;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false)
    private EmergencyMediaStatus mediaType;

    @Column(name = "storage_url", nullable = false, length = 255)
    private String storageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_location_id")
    private EmergencyLocation emergencyLocation;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
