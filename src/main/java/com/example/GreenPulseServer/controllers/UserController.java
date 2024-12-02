package com.example.GreenPulseServer.controllers;

import com.example.EarthlyServer.Messages;
import com.example.EarthlyServer.exceptions.UserNotFoundException;
import com.example.EarthlyServer.models.User;
import com.example.EarthlyServer.reponses.ApiResponse;
import com.example.EarthlyServer.reponses.UserResponse;
import com.example.EarthlyServer.requests.LogInRequest;
import com.example.EarthlyServer.requests.RegisterRequest;
import com.example.EarthlyServer.services.FirebaseStorageService;
import com.example.EarthlyServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    FirebaseStorageService firebaseStorageService;

    @GetMapping("/id/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable Long id)
    {
        User user = userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        UserResponse response = transformToResponse(user);
        return ApiResponse.success(Messages.RETRIEVED,response);
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse>registerUser(@RequestBody RegisterRequest request)
    {
        if(userService.ifEmailExists(request.getEmail()))
        {
            return ApiResponse.error("Email already exists!!!",400);
        }
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        User user = userService.saveUser(newUser);
        UserResponse response = transformToResponse(user);
        return ApiResponse.success(Messages.CREATED,response);
    }
    @PostMapping("/upload")
    public ApiResponse<String>uploadDP(@RequestParam("file") MultipartFile file,
                                       @RequestParam("email") String email) throws IOException {
        String filePath = firebaseStorageService.uploadFile(file);
        User user = userService.findByEmail(email);
        user.setDp(filePath);
        userService.saveUser(user);
        return ApiResponse.success("Image uploaded",filePath);
    }

    @PostMapping("/login")
    public ApiResponse<UserResponse> logInUser(@RequestBody LogInRequest request)
    {
        User user = userService.findByEmail(request.getEmail());
        if(user!=null)
        {
            if(user.getPassword().equals(request.getPassword()))
            {
                //found the user. log him in
                UserResponse userResponse = transformToResponse(user);
                return ApiResponse.success("Log in successful!!!",userResponse);
            }
        }
        return ApiResponse.error(Messages.NOT_FOUND,404);
    }

    @PutMapping("/update/id/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable Long id,
                                                @RequestBody User user)
    {
        User oldUser = userService.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id: "+id+" doesn't exist!!!"));
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        userService.saveUser(oldUser);
        UserResponse response = transformToResponse(oldUser);
        return ApiResponse.success(Messages.UPDATED,response);
    }

    @DeleteMapping("/delete/id/{id}")
    public ApiResponse<UserResponse>deleteUser(@PathVariable Long id)
    {
        User oldUser = userService.findById(id).orElseThrow(()->
                new UserNotFoundException("User with id: "+id+" doesn't exist!!!"));
        UserResponse response = transformToResponse(oldUser);
        userService.deleteUser(oldUser);
        return ApiResponse.success(Messages.UPDATED,response);
    }


    public UserResponse transformToResponse(User user)
    {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }



}
