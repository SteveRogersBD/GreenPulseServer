package com.example.GreenPulseServer.exceptions;

import com.example.GreenPulseServer.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleUserNotFound(UserNotFoundException ex) {
        return ApiResponse.error(ex.getMessage(),404);
    }
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handlePostNotFound(UserNotFoundException ex) {
        return ApiResponse.error(ex.getMessage(),404);
    }
    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleCommentNotFound(UserNotFoundException ex) {
        return ApiResponse.error(ex.getMessage(),404);
    }

    @ExceptionHandler(CropNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleCommentNotFound(CropNotFoundException ex) {
        return ApiResponse.error(ex.getMessage(),404);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleAllErrors(Exception ex) {
        return ApiResponse.error(ex.getMessage(),500);
    }
}
