package com.example.GreenPulseServer.services;

import com.example.EarthlyServer.models.Post;
import com.example.EarthlyServer.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public void savePost(Post post) {
        postRepo.save(post);
    }

    public void deletePost(Post post) {
        postRepo.delete(post);
    }
}
