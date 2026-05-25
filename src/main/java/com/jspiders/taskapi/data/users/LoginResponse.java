package com.jspiders.taskapi.data.users;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String name;
    private String message;

}
