package com.example.GreenPulseServer.services;

import com.example.GreenPulseServer.models.Crop;
import com.example.GreenPulseServer.repositories.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CropService {

    @Autowired
    CropRepo cropRepo;
    public List<Crop> getAllCrops() {
        List<Crop> crops = cropRepo.findAll();
        Collections.reverse(crops);
        return crops;
    }

    public Optional<Crop> getCropById(Long id) {
        return cropRepo.findById(id);
    }

    public Optional<Crop> getCropByName(String name) {
        return Optional.ofNullable(cropRepo.findByCropName(name));
    }

    public void deleteCropById(Long id) {
        cropRepo.deleteById(id);
    }

    public Crop insertCrop(Crop crop) {
        return cropRepo.save(crop);
    }
}
