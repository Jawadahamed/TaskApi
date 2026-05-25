package com.jspiders.taskapi.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> arthematicExceptionHandler(ArithmeticException ex){
        log.error("Handling ArithmeticException");
        ex.printStackTrace();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went Wrong buddy");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex){
        log.error("Handling NullPointerException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went Wrong buddy");
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex){
        log.error("Handling IllegalAccessException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user with given mobile/email already exist");
    }
    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<String> InvalidNameExceptionExceptionHandler(InvalidNameException ex){
        log.error("Handling InvalidNameException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("!Invalid Name");
    }


    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> InvalidEmailExceptionExceptionHandler(InvalidEmailException ex){
        log.error("Handling InvalidEmailException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email Address");
    }
    @ExceptionHandler(InvalidMobileException.class)
    public ResponseEntity<String> InvalidMobileExceptionExceptionHandler(InvalidMobileException ex){
        log.error("Handling InvalidMobileException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Mobile number");
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> InvalidPasswordExceptionExceptionHandler(InvalidPasswordException ex){
        log.error("Handling InvalidPasswordException");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("! Invalid Password");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {

//
//        FieldError fieldErrors = ex.getFieldError();
//        String field = fieldErrors.getField();
//        String errorMessage = fieldErrors.getDefaultMessage();
//        Map<String,String>errorMap=new HashMap<>();
//        errorMap.put(field,errorMessage);



        Map<String ,String> errorMap=new HashMap<>();
        List<FieldError > fieldErrors=ex.getFieldErrors();

        for(FieldError fieldError:fieldErrors)
        {
            String field = fieldError.getField();
       String erroMessage = fieldError.getDefaultMessage();
       errorMap.put(field,erroMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(InvalidTitleException.class)
    public ResponseEntity<String> invalidTitleExceptionHandler(InvalidTitleException ex){
        log.error("Invalid Title");
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Title");
    }


    @ExceptionHandler(InvalidDescriptionException.class)
    public ResponseEntity<String> invalidDescriptionExceptionHandler(InvalidDescriptionException ex){
        log.error("Invalid Description");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Description");
    }


    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> invalidDuplicteUserExceptionHandler(DuplicateUserException ex){
        log.error(" Duplicate user"+ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> invalidDuplicteUserExceptionHandler(NoSuchElementException ex){
        log.error(" handling NoSuchElementException ");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" DATA NOT FOUND ");
    }
}
