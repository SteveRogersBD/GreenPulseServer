package com.example.GreenPulseServer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long cropId;

    @Column(name = "crop_name", nullable = false)
    private String cropName;

    @Column(name = "scientific_name", nullable = false)
    private String scientificName;

    @Column(name = "crop_type")
    private String cropType;

    @Column(name = "optimal_temperature_min")
    private float optimalTemperatureMin;

    @Column(name = "optimal_temperature_max")
    private float optimalTemperatureMax;

    @Column(name = "soil_type")
    private String soilType;

    @Column(name = "soil_ph_min")
    private float soilPhMin;

    @Column(name = "soil_ph_max")
    private float soilPhMax;

    @Column(name = "water_requirement")
    private String waterRequirement;

    @Column(name = "sunlight_requirement")
    private String sunlightRequirement;

    @Column(name = "planting_season")
    private String plantingSeason;

    @Column(name = "harvesting_period")
    private String harvestingPeriod;

    @Column(name = "fertilizer_recommendations", columnDefinition = "TEXT")
    private String fertilizerRecommendations;

    @Column(name = "nutrient_requirements", columnDefinition = "TEXT")
    private String nutrientRequirements;

    @Column(name = "pests", columnDefinition = "TEXT")
    private String pests;

    @Column(name = "diseases", columnDefinition = "TEXT")
    private String diseases;

    @Column(name = "treatment_suggestions", columnDefinition = "TEXT")
    private String treatmentSuggestions;

    @Column(name = "irrigation_methods")
    private String irrigationMethods;

    @Column(name = "storage_conditions", columnDefinition = "TEXT")
    private String storageConditions;


}
