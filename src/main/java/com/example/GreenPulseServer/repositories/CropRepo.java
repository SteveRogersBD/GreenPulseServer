package com.example.GreenPulseServer.repositories;

import com.example.GreenPulseServer.models.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepo extends JpaRepository<Crop,Long> {
    Crop findByCropName(String name);
}
