package com.example.GreenPulseServer.services;

import com.example.EarthlyServer.models.User;
import com.example.EarthlyServer.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User saveUser(User user)
    {
        return userRepo.save(user);
    }

    public Optional<User> findById(Long id)
    {
        return userRepo.findById(id);
    }

    public void deleteUser(User user)
    {
        userRepo.delete(user);
    }

    public boolean ifEmailExists(String userEmail)
    {
        return userRepo.existsByEmail(userEmail);
    }
    public User findByEmail(String email)
    {
        return userRepo.findByEmail(email);
    }

    public List<User>getAllUsers(){
        return userRepo.findAll();
    }
}
