package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.task.Task;
import com.jspiders.taskapi.data.task.TaskDto;
import com.jspiders.taskapi.data.task.TaskRepository;
import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.errors.DuplicateUserException;
import com.jspiders.taskapi.services.AppUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserServiceImpl2 implements AppUserService {

    private final ObjectMapper mapper=new ObjectMapper();

    private final AppUserRepository appUserRepository;
    private final TaskRepository taskRepository;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {

        boolean exists= appUserRepository.existsByEmailOrMobile(createUserRequest.getEmail(),createUserRequest.getMobile());
        if (exists==true){
            throw new DuplicateUserException("user with given mobile/email already exist");
        }

     AppUser appUser =  mapper.convertValue(createUserRequest,AppUser.class);

        appUser.setActive(true);

        AppUser appUserInDb=appUserRepository.save(appUser);

        long userId =appUserInDb.getUserId();

        //build the response
        CreateUserResponse response=new CreateUserResponse();
        response.setUserId(userId);
        response.setMessage("user created");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Override
    public ResponseEntity<String> updateUser() {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteUser(String email, String mobile, String password) {
        return null;
    }



    @Override
    public ResponseEntity<List<AppUserDTO>> getAllUsers(@RequestHeader Long userId)
    {
        boolean isPresent = appUserRepository.existsById(userId);
        if(isPresent==false){
            throw new IllegalArgumentException("Security ERROR:UserId is not valid");
        }

        List<AppUser> appUsersDTOlist=appUserRepository.findAll();
        List<AppUserDTO>appUserDTOList=new ArrayList<>();


//Bussines Logic
        for(AppUser appUser:appUsersDTOlist){
            com.jspiders.taskapi.data.users.AppUserDTO appUserDTO =mapper.convertValue(appUser,AppUserDTO.class);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(appUserDTOList);    }

    @Override
    public ResponseEntity<AppUserDTO> getUsersById(Long userId) {
//        log.info("get user by id");
//
//        Optional<AppUser> optional = appUserRepository.findById(userId);
//        AppUser appUser=optional.get();
//
//      AppUserDTO response = mapper.convertValue(appUser,AppUserDTO.class);
//        List<Task> taskList = taskRepository.findAll();
//        response.setTaskList(taskList);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
        log.info("get user by id()");

        AppUser appUser = appUserRepository.findById(userId).orElseThrow();
        AppUserDTO appUserDTO = mapper.convertValue(appUser, AppUserDTO.class);

        //List<Task> taskList=taskRepository.findByAppUserUserId(userId);
        List<Task> taskList = taskRepository.findByAppUserUserId(userId);
        List<TaskDto>taskDtoList=new ArrayList<>();
        for(Task task:taskList){
            TaskDto taskDto=mapper.convertValue(task,TaskDto.class);
            taskDtoList.add(taskDto);
        }
        appUserDTO.setTaskList(taskList);
            return  ResponseEntity.status(HttpStatus.OK)
                 .body(appUserDTO);
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        log.info("inside login");
        String userId;
        LoginResponse loginResponse;

        boolean isPresent= appUserRepository.existsByEmailAndPassword(loginRequest.getEmail(),
                loginRequest.getPassword());


        if (isPresent=true) {
            Optional<AppUser> userOptional = appUserRepository.findByEmail(loginRequest.getEmail());
            AppUser appUser = userOptional.get();
            loginResponse = mapper.convertValue(appUser,LoginResponse.class);
            loginResponse.setMessage("Login Sucess");
//            userId = String.valueOf(appUser.getUserId());
        }
        else {
            throw new IllegalArgumentException("Invalid UserName/Password");
        }
        return ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<String> updateUserEmail(Long userId, UpdateUserEmailRequest updateUserEmailRequest) {
        log.info("updateuser email and user id");
        boolean isPresent = appUserRepository.existsById(userId);
if(isPresent==false){
    throw  new IllegalArgumentException("User with given  userid NOT FOUND!");
}
        Optional<AppUser> Appuseroptional = appUserRepository.findByEmailAndUserId(updateUserEmailRequest.getOldEmail(),
                updateUserEmailRequest.getUserId());

        if(isPresent==false){
            throw  new IllegalArgumentException("User with given email and userid NOT FOUND!");
        }
        else {
          AppUser appUser= Appuseroptional.get();
            appUser.setEmail(updateUserEmailRequest.getNewEmail());
            appUserRepository.save(appUser);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("User with email updated successfully");
    }
}
