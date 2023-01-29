package com.practice.khstudy.controller;

import com.practice.khstudy.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
//    public Map<String, String> invalidRequestHandler(MethodArgumentNotValidException e) {
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {

        ErrorResponse response = new ErrorResponse("400", "잘못된 요청입니다.");
        for (FieldError fieldError : e.getFieldErrors()) {

            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

//        FieldError fieldError = e.getFieldError();
//        String field = fieldError.getField();
//        String message = fieldError.getDefaultMessage();
//        Map<String, String> response = new HashMap<>();
//        response.put(field, message);
        return response;
    }
}
