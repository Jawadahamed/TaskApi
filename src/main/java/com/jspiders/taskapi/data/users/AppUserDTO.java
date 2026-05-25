package com.jspiders.taskapi.data.users;

import com.jspiders.taskapi.data.task.Task;
import lombok.Data;

import java.util.List;

@Data
public class AppUserDTO {
    private Long userId;
   private String name;
    private String email;
    private String mobile;
    private boolean isActive;
    private List<Task> taskList;

}
