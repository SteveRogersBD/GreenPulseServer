package com.example.GreenPulseServer.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private int statusCode;
    private T data;

    public static <T>ApiResponse<T>success(String message, T data) {
        return new ApiResponse<T>("Success",message,200,data);
    }
    public static <T>ApiResponse<T>error(String message,int errorCode)
    {
        return new ApiResponse<T>("Error",message,errorCode,null);
    }

}
