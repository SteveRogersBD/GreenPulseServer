package com.example.GreenPulseServer.repositories;

import com.example.EarthlyServer.models.LocationEvent;
import com.example.EarthlyServer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<LocationEvent,Long> {
    List<LocationEvent> findByUser(User user);
}
