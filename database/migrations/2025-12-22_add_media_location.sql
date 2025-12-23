-- Agregar columna de ubicación y timestamp a emergency_media
ALTER TABLE emergency_media
    ADD COLUMN emergency_location_id BIGINT NULL,
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- FK a emergency_locations con borrado que pone NULL
ALTER TABLE emergency_media
    ADD CONSTRAINT fk_emergency_media_location
        FOREIGN KEY (emergency_location_id)
        REFERENCES emergency_locations(id)
        ON DELETE SET NULL;

-- Índices para consultas por evento y ubicación
CREATE INDEX IF NOT EXISTS idx_emergency_media_event ON emergency_media(emergency_event_id);
CREATE INDEX IF NOT EXISTS idx_emergency_media_location ON emergency_media(emergency_location_id);
