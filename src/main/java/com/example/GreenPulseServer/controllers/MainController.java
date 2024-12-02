package com.example.GreenPulseServer.controllers;

import com.example.EarthlyServer.Messages;
import com.example.EarthlyServer.models.Post;
import com.example.EarthlyServer.models.User;
import com.example.EarthlyServer.reponses.ApiResponse;
import com.example.EarthlyServer.reponses.PostResponse;
import com.example.EarthlyServer.reponses.UserResponse;
import com.example.EarthlyServer.services.PostService;
import com.example.EarthlyServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    UserController userController;
    @Autowired
    PostService postService;
    @Autowired
    PostController postController;

    @GetMapping("/all-users")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponse>data = new ArrayList<>();
        for (User user : users) {
            data.add(userController.transformToResponse(user));
        }
        return ApiResponse.success(Messages.RETRIEVED,data);
    }
    @GetMapping("/all-posts")
    public ApiResponse<List<PostResponse>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostResponse>data = new ArrayList<>();
        for (Post post : posts) {
            data.add(postController.transformToResponse(post));
        }
        return ApiResponse.success(Messages.RETRIEVED,data);
    }

}
