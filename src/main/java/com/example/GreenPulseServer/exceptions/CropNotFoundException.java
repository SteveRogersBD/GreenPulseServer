package com.example.GreenPulseServer.exceptions;

public class CropNotFoundException extends RuntimeException {
    public CropNotFoundException(String message) {
        super(message);
    }
    public CropNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
