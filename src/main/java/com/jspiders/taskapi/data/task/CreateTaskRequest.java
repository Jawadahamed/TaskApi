package com.jspiders.taskapi.data.task;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateTaskRequest {
   // @Length(min = 3,max = 45,message = "Title should be min 3 and max 45 character only")
    private String title;

    //@Length(min = 3,max = 45,message = "Description should be min 5 and max 45 character only")
    private String description;

    private String status;

    private Long userId;



}
