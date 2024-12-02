package com.example.GreenPulseServer.controllers;

import com.example.EarthlyServer.Messages;
import com.example.EarthlyServer.exceptions.PostNotFoundException;
import com.example.EarthlyServer.exceptions.UserNotFoundException;
import com.example.EarthlyServer.models.Post;
import com.example.EarthlyServer.models.User;
import com.example.EarthlyServer.reponses.ApiResponse;
import com.example.EarthlyServer.reponses.PostResponse;
import com.example.EarthlyServer.requests.PostRequest;
import com.example.EarthlyServer.services.FirebaseStorageService;
import com.example.EarthlyServer.services.PostService;
import com.example.EarthlyServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    FirebaseStorageService firebaseStorageService;

    @GetMapping("/get/id/{id}")
    public ApiResponse<PostResponse> getId(@PathVariable Long id) {
        Post post = postService.getPostById(id).orElseThrow(()->
                new PostNotFoundException("Post with id "+id+" doesn't exist."));
        PostResponse response = transformToResponse(post);
        return ApiResponse.success(Messages.RETRIEVED, response);

    }

    //create a post
    @PostMapping("/create/user/{userId}")
    public ApiResponse<PostResponse>createPost(@RequestParam("title") String title,
                                               @RequestParam("content") String content,
                                               @RequestParam("file") MultipartFile file,
                                               @PathVariable Long userId) throws IOException {
        User user = userService.findById(userId).orElseThrow(()->
                new UserNotFoundException("User with id "+userId+" doesn't exist."));

        //if the user is found then we proceed with the further logic
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        String filePath = firebaseStorageService.uploadFile(file);
        post.setImage(filePath);
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postService.savePost(post);
        PostResponse response = transformToResponse(post);
        return ApiResponse.success(Messages.CREATED, response);

    }

    //update a post
    @PutMapping("/update/{id}")
    public ApiResponse<PostResponse> updatePost(PostRequest request,
                                                @PathVariable Long id)
    {
        Post post = postService.getPostById(id).orElseThrow(()->
                new PostNotFoundException("Post with id "+id+" doesn't exist."));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(LocalDateTime.now());
        postService.savePost(post);
        PostResponse response = transformToResponse(post);
        return ApiResponse.success(Messages.UPDATED, response);

    }

    //delete a post
    @DeleteMapping("/delete/{id}")
    public ApiResponse<PostResponse> deletePost(@RequestBody PostRequest request,
                                                @PathVariable Long id)
    {
        Post post = postService.getPostById(id).orElseThrow(()->
                new PostNotFoundException("Post with id "+id+" doesn't exist."));
        postService.deletePost(post);
        PostResponse response = transformToResponse(post);
        return ApiResponse.success(Messages.DELETED, response);

    }




    public PostResponse transformToResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setImage(post.getImage());
        postResponse.setCreatedAt(post.getCreatedAt());
        postResponse.setUpdatedAt(post.getUpdatedAt());
        return postResponse;

    }
}
