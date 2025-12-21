package com.cibertec.ApiNova.notification.dto;

import lombok.Data;
import java.util.List;

@Data
public class EmergencyBroadcastRequest {
    private List<String> phoneNumbers; 
    private String userName;        
    private String googleMapsLink;    
}