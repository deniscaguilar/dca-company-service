package com.dca.company.controller.controller.exception;

import com.dca.company.exception.InvalidRequestException;
import com.dca.company.exception.ResourceNotFoundException;
import com.dca.company.controller.io.ErrorResource;
import com.dca.company.controller.io.FieldErrorResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ErrorResource errorResource(String code, String message) {
        return new ErrorResource(code, message);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException e, WebRequest request) {
        ErrorResource error = errorResource("NotFound", e.getMessage());
        return handleExceptionInternal(e, error, getHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        InvalidRequestException ire = (InvalidRequestException) e;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();
        List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setResource(fieldError.getObjectName());
            fieldErrorResource.setField(fieldError.getField());
            fieldErrorResource.setCode(fieldError.getCode());
            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
            fieldErrorResources.add(fieldErrorResource);
        }
        ErrorResource error = errorResource("InvalidRequest", ire.getMessage());
        error.setFieldErrors(fieldErrorResources);
        return handleExceptionInternal(e, error, getHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGlobalException(Exception e, WebRequest request) {
        ErrorResource error = errorResource("UnexpectedError", e.getMessage());
        return handleExceptionInternal(e, error, getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleBadRequestException(final HttpClientErrorException e, WebRequest request) {
        ErrorResource error = errorResource("BadRequest", e.getMessage());
        return handleExceptionInternal(e, error, getHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException e, WebRequest request) {
        ErrorResource error = errorResource("BadRequest", e.getMessage());
        return handleExceptionInternal(e, error, getHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException e, WebRequest request) {
        ErrorResource error = errorResource("BadGateway", e.getMessage());
        return handleExceptionInternal(e, error, getHeaders(), HttpStatus.BAD_GATEWAY, request);
    }

}