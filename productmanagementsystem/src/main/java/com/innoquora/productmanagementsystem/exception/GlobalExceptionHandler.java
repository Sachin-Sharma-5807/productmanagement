package com.innoquora.productmanagementsystem.exception;

import com.innoquora.productmanagementsystem.payload.ErrorResposne;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResposne<List<String>>> handleProductNotFound(ProductNotFoundException ex) {
        return buildError("Product Not Found", List.of(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResposne<List<String>>> handleUserNotFound(UserNotFoundException ex) {
        return buildError("User Not Found", List.of(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResposne<List<String>>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return buildError("User Already Exists", List.of(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResposne<List<String>>> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();
        return buildError("Validation Failed", errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResposne<List<String>>> handleException(Exception ex) {
        return buildError("Something went wrong", List.of(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResposne<List<String>>> buildError(String message, List<String> errors, HttpStatus status) {
        ErrorResposne<List<String>> response = ErrorResposne.<List<String>>builder()
                .status(false)
                .message(message)
                .data(errors)
                .timestamp(LocalDate.now())
                .build();
        return ResponseEntity.status(status).body(response);
    }
}