package com.example.GreenPulseServer.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    private Long eventId;
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private LocalDateTime eventTime;
    private LocalDateTime createdAt;
    private UserResponse createdBy;
}
