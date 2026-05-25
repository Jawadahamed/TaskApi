package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.services.AppUserService;
import com.jspiders.taskapi.services.impl.AppUserServiceImpl;
import com.jspiders.taskapi.services.impl.AppUserServiceImpl2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
    AppUserService appUserService;
//      private  final Logger logger =LoggerFactory.getLogger(UserController.class);this line of code is or we can use this  @Slf4j
    @Autowired
    public UserController(AppUserServiceImpl2 appUserService){
        this.appUserService=appUserService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> addUser(@RequestBody @Valid CreateUserRequest createUserRequest){

        log.info("This is UserController --> addUser() createUserRequest: {}",createUserRequest);


        ResponseEntity<CreateUserResponse> response = appUserService.createUser(createUserRequest);
        log.info("inside addUser() : User created");
        return response;
    }

    @PatchMapping("/updateEmail")
    public ResponseEntity<String> updateUserEmail(@RequestHeader Long userId ,@RequestBody @Valid UpdateUserEmailRequest updateUserEmailRequest){
        log.info("updateUser()");

        ResponseEntity<String> response = appUserService.updateUser();
        return response;
    }

    @DeleteMapping
    ResponseEntity<String> deleteUser(String email,String mobile,String password){
        log.info("DeleteUser()");

        ResponseEntity<String> response = appUserService.deleteUser(email,mobile,password);
        return response;
    }


    @GetMapping
    ResponseEntity<List<AppUserDTO>> getAllUsers(@RequestHeader Long userId){
        log.info("getallUsers()");

        ResponseEntity<List<AppUserDTO>> response = appUserService.getAllUsers( userId);
        return response;
    }

    @GetMapping("/{userId}")
    ResponseEntity<AppUserDTO> getUsersById(@RequestHeader Long userId){
        log.info("getUsersById()");
        log.info("userId {}",userId);

        ResponseEntity<AppUserDTO> response = appUserService.getUsersById(userId);
        return response;
    }

    @PostMapping("/login")
    public  ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        log.info("login()");

        ResponseEntity<LoginResponse>response=appUserService.login(loginRequest);
        return response;

    }
}
