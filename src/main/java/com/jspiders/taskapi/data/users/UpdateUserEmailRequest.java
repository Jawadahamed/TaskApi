package com.jspiders.taskapi.data.users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserEmailRequest {
    @NotBlank(message = "New email Cannot be Empty")
    private String newEmail;

    @NotBlank(message = "old email Cannot be Empty")
    private String oldEmail;

    @NotBlank(message = "UserId Cannot be Empty")
    private Long userId;

}
