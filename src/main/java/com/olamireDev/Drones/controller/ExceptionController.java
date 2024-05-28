package com.olamireDev.Drones.controller;

import com.olamireDev.Drones.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleBadRequestException(Exception exception) {
        if(exception instanceof BadRequestException badRequestException){
            return ResponseEntity.badRequest().body(badRequestException.getMessage());
        }
        if( exception instanceof MethodArgumentNotValidException methodArgumentNotValidException){
            String[] message = methodArgumentNotValidException.getMessage().split(" default message ");
            var lastMessage = message[message.length - 1];
            lastMessage = lastMessage.substring(1, lastMessage.length() - 3);
            return ResponseEntity.badRequest().body(lastMessage);
        }
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }
}
