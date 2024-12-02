package com.example.GreenPulseServer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LocationEvent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long eventId;
    private String title;
    private String description;
    private double latitude;
    private double longitude;
    private LocalDateTime eventTime;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
