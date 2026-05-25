package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.users.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AppUserService {
    ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest);
    ResponseEntity<String> updateUser();
    ResponseEntity<String> deleteUser(String email,String mobile,String password);
    ResponseEntity<List<AppUserDTO>> getAllUsers(Long userId);
    ResponseEntity<AppUserDTO> getUsersById(Long userId);
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
    ResponseEntity<String> updateUserEmail(Long userId,UpdateUserEmailRequest updateUserEmailRequest);
}
