package com.example.GreenPulseServer.controllers;


import com.example.GreenPulseServer.Messages;
import com.example.GreenPulseServer.exceptions.CropNotFoundException;
import com.example.GreenPulseServer.models.Crop;
import com.example.GreenPulseServer.responses.ApiResponse;
import com.example.GreenPulseServer.services.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crop")
public class CropController {

    @Autowired
    CropService cropService;

    @GetMapping("/get/{id}")
    public ApiResponse<Crop> getCropById(@PathVariable Long id) {
        Crop crop = cropService.getCropById(id).orElseThrow(()->{
            return new CropNotFoundException("Crop with id "+id+" not found");
        });
        return ApiResponse.success(Messages.RETRIEVED, crop);
    }

    // Create a new crop
    @PostMapping("/create")
    public ApiResponse<Crop> createCrop(@RequestBody Crop crop) {
        Crop createdCrop = cropService.insertCrop(crop);
        return ApiResponse.success(Messages.CREATED, createdCrop);
    }

    // Delete a crop by ID
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Crop> deleteCropById(@PathVariable Long id) {
        Crop crop = cropService.getCropById(id).orElseThrow(()->{
            return new CropNotFoundException("Crop with id "+id+" not found");
        });
        cropService.deleteCropById(id);
        return ApiResponse.success(Messages.DELETED, crop);
    }

    // Get all crops
    @GetMapping("/get/all")
    public ApiResponse<List<Crop>> getAllCrops() {
        List<Crop> crops = cropService.getAllCrops();
        return ApiResponse.success(Messages.RETRIEVED, crops);
    }

    // Get crop by name
    @GetMapping("/get/name/{name}")
    public ApiResponse<Crop> getCropByName(@PathVariable String name) {
        Crop crop = cropService.getCropByName(name).orElseThrow(() ->
                new CropNotFoundException("Crop with name " + name + " not found"));
        return ApiResponse.success(Messages.RETRIEVED, crop);
    }



}
