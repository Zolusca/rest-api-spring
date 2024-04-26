package com.id.vonsan.jpastarter.Exception;

import com.id.vonsan.jpastarter.DTO.ApiErrorException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiErrorException> notFoundExceptionHandler(NotFoundException errors){

        List<String> errorsData = new ArrayList<>(Collections.singleton(errors.getMessage()));


        ApiErrorException apiErrorException = new ApiErrorException();
        apiErrorException.setStatus(HttpStatus.NOT_FOUND.toString());
        apiErrorException.setErrors(errorsData);

        return new ResponseEntity<>(apiErrorException,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistException.class)
    public ResponseEntity<ApiErrorException> alreadyExistException(AlreadyExistException errors){
     
        List<String> errorsData = new ArrayList<>(Collections.singleton(errors.getMessage()));

        ApiErrorException apiErrorException = new ApiErrorException();
        apiErrorException.setStatus(HttpStatus.CONFLICT.toString());
        apiErrorException.setErrors(errorsData);

        return new ResponseEntity<>(apiErrorException,HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiErrorException> constraintValidationException(ConstraintViolationException errors){

        List<String> errorsData = new ArrayList<>(Collections.singleton(errors.getMessage()));

        ApiErrorException apiErrorException = new ApiErrorException();
        apiErrorException.setStatus(HttpStatus.BAD_REQUEST.toString());
        apiErrorException.setErrors(errorsData);

        return new ResponseEntity<>(apiErrorException,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorException> methodArgumentValidationException(MethodArgumentNotValidException errors){

        List<String> errorsData = errors.getAllErrors().stream()
                                   .map(t -> t.getDefaultMessage())
                                   .collect(Collectors.toList());

        ApiErrorException apiErrorException = new ApiErrorException();
        apiErrorException.setStatus(HttpStatus.BAD_REQUEST.toString());
        apiErrorException.setErrors(errorsData);

        return new ResponseEntity<>(apiErrorException,HttpStatus.BAD_REQUEST);
    }

    
}
