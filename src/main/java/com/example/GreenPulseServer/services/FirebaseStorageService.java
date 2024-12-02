package com.example.GreenPulseServer.services;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    public String uploadFile(MultipartFile file) throws IOException {
        // Generate a unique filename
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Get the bucket
        Bucket bucket = StorageClient.getInstance().bucket();

        // Upload the file
        Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

        // Remove ".appspot.com" from the bucket name if it is present twice
        String bucketName = bucket.getName().replace(".appspot.com", "");

        // Generate the correct URL for the uploaded file
        String fileUrl = String.format(
                "https://firebasestorage.googleapis.com/v0/b/%s.appspot.com/o/%s?alt=media",
                bucketName,
                fileName.replace(" ", "%20") // Ensure URL encoding (replace spaces with '%20')
        );

        return fileUrl;
    }
}
