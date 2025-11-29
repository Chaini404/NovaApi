
INSERT INTO users (full_name, email, phone, password_hash, status) VALUES
('Carlos Correa', 'carlos1@gmail.com', '999888001', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE'),
('Ana Pérez', 'ana2@gmail.com', '999888002', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE'),
('Luis Martínez', 'luis3@gmail.com', '999888003', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE'),
('Marta Gómez', 'marta4@gmail.com', '999888004', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'INACTIVE'),
('Jorge Ramírez', 'jorge5@gmail.com', '999888005', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE'),
('Sofía Torres', 'sofia6@gmail.com', '999888006', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE'),
('Pedro Castillo', 'pedro7@gmail.com', '999888007', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'INACTIVE'),
('Lucía Díaz', 'lucia8@gmail.com', '999888008', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE'),
('Diego Herrera', 'diego9@gmail.com', '999888009', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE'),
('Carla Rojas', 'carla10@gmail.com', '999888010', '$2a$10$Tx5T9eJpZkZx0IZMMEZe7uWfHoTiaRt4v2tPzzmNdjwrojDvCrQhC', 'ACTIVE');


INSERT INTO contacts (user_id, full_name, phone_number, email, enable_whatsapp) VALUES
(1, 'Mamá Carlos', '900111001', 'mama.carlos@gmail.com', TRUE),
(1, 'Hermano Carlos', '900111002', 'hermano.carlos@gmail.com', FALSE),
(2, 'Padre Ana', '900222001', 'padre.ana@gmail.com', TRUE),
(2, 'Hermana Ana', '900222002', 'hermana.ana@gmail.com', TRUE),
(3, 'Mamá Luis', '900333001', 'mama.luis@gmail.com', TRUE),
(3, 'Hermano Luis', '900333002', 'hermano.luis@gmail.com', FALSE),
(4, 'Padre Marta', '900444001', 'padre.marta@gmail.com', TRUE),
(5, 'Mamá Jorge', '900555001', 'mama.jorge@gmail.com', TRUE),
(6, 'Hermana Sofía', '900666001', 'hermana.sofia@gmail.com', TRUE),
(7, 'Mamá Pedro', '900777001', 'mama.pedro@gmail.com', TRUE);


INSERT INTO emergency_events (user_id, event_type, activated_at, closed_at, resolved) VALUES
(1, 'VOICE', NOW() - INTERVAL 2 HOUR, NULL, FALSE),
(2, 'TAP', NOW() - INTERVAL 1 HOUR, NOW() - INTERVAL 30 MINUTE, TRUE),
(3, 'VOICE', NOW() - INTERVAL 3 HOUR, NULL, FALSE),
(4, 'TAP', NOW() - INTERVAL 5 HOUR, NOW() - INTERVAL 4 HOUR, TRUE),
(5, 'VOICE', NOW() - INTERVAL 2 HOUR, NULL, FALSE),
(6, 'TAP', NOW() - INTERVAL 6 HOUR, NOW() - INTERVAL 5 HOUR, TRUE),
(7, 'VOICE', NOW() - INTERVAL 1 HOUR, NULL, FALSE),
(8, 'TAP', NOW() - INTERVAL 3 HOUR, NOW() - INTERVAL 2 HOUR, TRUE),
(9, 'VOICE', NOW() - INTERVAL 4 HOUR, NULL, FALSE),
(10, 'TAP', NOW() - INTERVAL 2 HOUR, NOW() - INTERVAL 1 HOUR, TRUE);

INSERT INTO emergency_locations (emergency_event_id, latitude, longitude) VALUES
(1, -12.046374, -77.042793),
(1, -12.046400, -77.042800),
(2, -12.045000, -77.043000),
(2, -12.045100, -77.043100),
(3, -12.047000, -77.041500),
(3, -12.047100, -77.041600),
(4, -12.048000, -77.044000),
(4, -12.048100, -77.044100),
(5, -12.049000, -77.045000),
(5, -12.049100, -77.045100),
(6, -12.050000, -77.046000),
(6, -12.050100, -77.046100),
(7, -12.051000, -77.047000),
(7, -12.051100, -77.047100),
(8, -12.052000, -77.048000),
(8, -12.052100, -77.048100),
(9, -12.053000, -77.049000),
(9, -12.053100, -77.049100),
(10, -12.054000, -77.050000),
(10, -12.054100, -77.050100);

INSERT INTO emergency_media (emergency_event_id, media_type, storage_url) VALUES
(1, 'AUDIO', 'https://azurestorage.com/nova/audio_001.mp3'),
(1, 'PHOTO', 'https://azurestorage.com/nova/photo_001.jpg'),
(2, 'AUDIO', 'https://azurestorage.com/nova/audio_002.mp3'),
(2, 'PHOTO', 'https://azurestorage.com/nova/photo_002.jpg'),
(3, 'AUDIO', 'https://azurestorage.com/nova/audio_003.mp3'),
(3, 'PHOTO', 'https://azurestorage.com/nova/photo_003.jpg'),
(4, 'AUDIO', 'https://azurestorage.com/nova/audio_004.mp3'),
(4, 'PHOTO', 'https://azurestorage.com/nova/photo_004.jpg'),
(5, 'AUDIO', 'https://azurestorage.com/nova/audio_005.mp3'),
(5, 'PHOTO', 'https://azurestorage.com/nova/photo_005.jpg'),
(6, 'AUDIO', 'https://azurestorage.com/nova/audio_006.mp3'),
(6, 'PHOTO', 'https://azurestorage.com/nova/photo_006.jpg'),
(7, 'AUDIO', 'https://azurestorage.com/nova/audio_007.mp3'),
(7, 'PHOTO', 'https://azurestorage.com/nova/photo_007.jpg'),
(8, 'AUDIO', 'https://azurestorage.com/nova/audio_008.mp3'),
(8, 'PHOTO', 'https://azurestorage.com/nova/photo_008.jpg'),
(9, 'AUDIO', 'https://azurestorage.com/nova/audio_009.mp3'),
(9, 'PHOTO', 'https://azurestorage.com/nova/photo_009.jpg'),
(10, 'AUDIO', 'https://azurestorage.com/nova/audio_010.mp3'),
(10, 'PHOTO', 'https://azurestorage.com/nova/photo_010.jpg');
