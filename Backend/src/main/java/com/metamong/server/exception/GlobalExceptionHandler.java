package com.metamong.server.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity handleApplicationException(ApplicationException e){
        e.printStackTrace();
        if(e.getObj() != null){
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            map.put("data", e.getObj());
            return ResponseEntity.status(e.getStatus()).body(map);
        }
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        e.printStackTrace();
        return e.getMessage();
    }
}
