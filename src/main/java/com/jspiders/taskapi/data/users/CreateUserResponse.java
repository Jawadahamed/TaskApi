package com.jspiders.taskapi.data.users;

import lombok.Data;

@Data
public class CreateUserResponse {
    private Long userId;
    private String message;
}
