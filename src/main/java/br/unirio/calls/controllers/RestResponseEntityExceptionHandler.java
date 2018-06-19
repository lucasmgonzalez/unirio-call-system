package br.unirio.calls.controllers;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.google.gson.Gson;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.unirio.calls.core.ApiError;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
        Gson encoder = new Gson();
        ApiError apiError = new ApiError();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            apiError.addError(error.getField(), error.getDefaultMessage());
        }

        String body = encoder.toJson(apiError.getAllErrors());

        headers.add("Content-Type", "application/json;charset=utf-8");

        return handleExceptionInternal(ex, body, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}