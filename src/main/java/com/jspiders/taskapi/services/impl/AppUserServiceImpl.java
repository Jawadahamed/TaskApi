package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.users.*;
import com.jspiders.taskapi.errors.InvalidEmailException;
import com.jspiders.taskapi.errors.InvalidMobileException;
import com.jspiders.taskapi.errors.InvalidNameException;
import com.jspiders.taskapi.errors.InvalidPasswordException;
import com.jspiders.taskapi.services.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

@Service
@Slf4j//or @Component  also can be used
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private static Map<Long,AppUser> userDb=new HashMap<>();
    private final ObjectMapper mapper;

    private final AppUserRepository appUserRepository;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {
        log.info("inside createUser() {}",createUserRequest);


        //validate  validateName(createUserRequest);


//        execute bussiness logic


        //save data to database
//        Long userId=saveUser(createUserRequest);


        AppUser appUser = mapper.convertValue(createUserRequest,AppUser.class);
        appUser.setActive(true);

      AppUser appUser1 = appUserRepository.save(appUser);
      Long userId= appUser1.getUserId();


//        buildResponse object
        CreateUserResponse response=new CreateUserResponse();
        response.setMessage("User created");
        response.setUserId(userId);

        log.info("inside createUser() : user created");

//return response
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    public ResponseEntity<String> updateUser() {
       log.info("UpdateUser():");


        //logics

        return  ResponseEntity.status(HttpStatus.CREATED)
                .body("User Updated");
    }

    @Override
    public ResponseEntity<String> deleteUser(String email, String mobile, String password) {
        System.out.println("This is AppuserServiceimpl -->deleteUser()");
        //Logics
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body("User Deleted Successfully");
    }

    @Override
    public ResponseEntity<List<AppUserDTO>> getAllUsers(Long userId) {
        log.info("getAllUser()");

        //database ops (Get All users From db)
        Collection<AppUser> values = userDb.values();
        List<AppUser> appUsersDTOlist= new ArrayList<>(values);
        List<AppUserDTO>appUserDTOList=new ArrayList<>();


//Bussines Logic
for(AppUser appUser:appUsersDTOlist){
 com.jspiders.taskapi.data.users.AppUserDTO appUserDTO =mapper.convertValue(appUser,AppUserDTO.class);
}

        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }



    @Override
    public ResponseEntity<AppUserDTO> getUsersById(Long userById)
    {
     log.info("getUsersById()");
// execute business logic

        //perform db operations(GET USER FROM DB)
        AppUser appUser= userDb.get(userById);

//        //build response object
//        AppUserDTO response=new AppUserDTO();
//
//        response.setName(response.getName());
//        response.setEmail(response.getEmail());
//        response.setMobile(response.getMobile());
//        response.setActive(response.isActive());

       AppUserDTO response =mapper.convertValue(appUser,AppUserDTO.class);


        //return response object
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }




    private void validateName(CreateUserRequest createUserRequest){
        if(createUserRequest.getName() != null && createUserRequest.getName().length()<3){
            InvalidNameException ex= new InvalidNameException("! Invalid Name");
            throw ex;
        }
      }

    private void validateEmail(CreateUserRequest createUserRequest){
        if(createUserRequest.getEmail() != null && createUserRequest.getEmail().length()<8){
            InvalidEmailException ex = new InvalidEmailException("Invalid Email");
            throw ex;
        }
    }

    private void validateMobile(CreateUserRequest createUserRequest){
        if(createUserRequest.getMobile() != null && createUserRequest.getMobile().length()<10){
            InvalidMobileException ex=new InvalidMobileException("Invalid Mobile number");
            throw ex;
        }
    }

    private void validatePassword(CreateUserRequest createUserRequest){
        if(createUserRequest.getMobile() != null && createUserRequest.getPassword().length()<5){
            InvalidPasswordException ex=new InvalidPasswordException("Password should be atleast 5 characters");
            throw ex;
        }
    }

    private Long saveUser(CreateUserRequest createUserRequest){
        AppUser appUser=new AppUser();//create a row/record in db

        appUser.setName(createUserRequest.getName());
        appUser.setEmail(createUserRequest.getEmail());
        appUser.setMobile(createUserRequest.getMobile());
        appUser.setPassword(createUserRequest.getPassword());

        Random random=new Random();
        Long userId=random.nextLong();

        appUser.setUserId(userId);
        appUser.setActive(true);

        userDb.put(userId,appUser);// save data to db

    return userId;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateUserEmail(Long userId, UpdateUserEmailRequest updateUserEmailRequest) {
        return null;
    }
}
