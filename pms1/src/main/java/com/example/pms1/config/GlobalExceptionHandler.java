package com.example.pms1.config;

import com.example.pms1.dto.HttpStatus;
import com.example.pms1.dto.Resp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Resp<?> handleException(Exception e) {
        e.printStackTrace();
        return Resp.error(HttpStatus.ERROR, e.getMessage());
    }

}
