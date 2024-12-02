package com.example.GreenPulseServer.controllers;

import com.example.EarthlyServer.Messages;
import com.example.EarthlyServer.exceptions.UserNotFoundException;
import com.example.EarthlyServer.models.LocationEvent;
import com.example.EarthlyServer.models.User;
import com.example.EarthlyServer.reponses.ApiResponse;
import com.example.EarthlyServer.reponses.EventResponse;
import com.example.EarthlyServer.requests.EventRequest;
import com.example.EarthlyServer.services.LocationService;
import com.example.EarthlyServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;

    @PostMapping("/create/user/{userId}")
    public ApiResponse<EventResponse>createEvent(@PathVariable Long userId,
                                                 @RequestBody EventRequest request)
    {
        User user = userService.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("User with id " + userId + " not found")
        );
        LocationEvent locationEvent = new LocationEvent();
        locationEvent.setUser(user);
        locationEvent.setTitle(request.getTitle());
        locationEvent.setDescription(request.getDescription());
        locationEvent.setLatitude(request.getLatitude());
        locationEvent.setLongitude(request.getLongitude());
        locationEvent.setCreatedAt(LocalDateTime.now());
        locationEvent.setEventTime(request.getEventTime());
        LocationEvent saved = locationService.saveLocation(locationEvent);
        EventResponse response = transformToResponse(saved);
        return ApiResponse.success(Messages.CREATED,response);

    }
    @GetMapping("/get/{id}")
    public ApiResponse<EventResponse>getById(@PathVariable Long id)
    {
        LocationEvent event = locationService.getLocationById(id).orElseThrow(
                ()->new IllegalArgumentException("Location with id " + id + " not found")
        );
        EventResponse response = transformToResponse(event);
        return ApiResponse.success(Messages.RETRIEVED,response);
    }
    @PutMapping("/update/{id}")
    public ApiResponse<EventResponse>updateEvent(@PathVariable Long id,
                                                 @RequestParam String title,
                                                 @RequestParam String desc,
                                                 @RequestParam LocalDateTime eventTime)
    {
        LocationEvent event = locationService.getLocationById(id).orElseThrow(
                ()->new IllegalArgumentException("Location with id " + id + " not found")
        );
        event.setTitle(title);
        event.setDescription(desc);
        event.setEventTime(eventTime);
        LocationEvent saved = locationService.saveLocation(event);
        EventResponse response = transformToResponse(event);
        return ApiResponse.success(Messages.UPDATED,response);
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<EventResponse>deleteEvent(@PathVariable Long id)
    {
        LocationEvent event = locationService.getLocationById(id).orElseThrow(
                ()->new IllegalArgumentException("Location with id " + id + " not found")
        );
        EventResponse response = transformToResponse(event);
        locationService.deleteLocation(event);
        return ApiResponse.success(Messages.DELETED,response);
    }
    @GetMapping("/get-all/user/{userId}")
    public ApiResponse<List<EventResponse>>getEventsByUser(@PathVariable Long userId)
    {
        User user = userService.findById(userId).orElseThrow(
                ()->new UserNotFoundException("User with id " + userId + " not found")
        );
        List<LocationEvent>events = locationService.getAllEventsByUser(user);
        List<EventResponse>responses = new ArrayList<>();
        for(LocationEvent event : events)
        {
            EventResponse response = transformToResponse(event);
            responses.add(response);
        }
        return ApiResponse.success(Messages.RETRIEVED,responses);
    }

    public EventResponse transformToResponse(LocationEvent event)
    {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(event.getEventId());
        eventResponse.setTitle(event.getTitle());
        eventResponse.setDescription(event.getDescription());
        eventResponse.setEventTime(event.getEventTime());
        eventResponse.setCreatedAt(event.getCreatedAt());
        eventResponse.setLatitude(event.getLatitude());
        eventResponse.setLongitude(event.getLongitude());
        eventResponse.setCreatedBy(userController.transformToResponse(event.getUser()));
        return eventResponse;
    }
}
