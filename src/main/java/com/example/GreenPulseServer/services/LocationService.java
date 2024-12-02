package com.example.GreenPulseServer.services;

import com.example.EarthlyServer.models.LocationEvent;
import com.example.EarthlyServer.models.User;
import com.example.EarthlyServer.repositories.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepo locationRepo;

    public LocationEvent saveLocation(LocationEvent locationEvent) {
        return locationRepo.save(locationEvent);
    }

    public Optional<LocationEvent> getLocationById(Long id) {
        return locationRepo.findById(id);
    }

    public List<LocationEvent> getAllLocationEvents() {
        return locationRepo.findAll();
    }

    public void deleteLocation(LocationEvent locationEvent) {
        locationRepo.delete(locationEvent);
    }

    public LocationEvent saveLocationEvent(LocationEvent locationEvent) {
        return locationRepo.save(locationEvent);
    }

    public List<LocationEvent>getAllEventsByUser(User user)
    {
        return locationRepo.findByUser(user);
    }

}