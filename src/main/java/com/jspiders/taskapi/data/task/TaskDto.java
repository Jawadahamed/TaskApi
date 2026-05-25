package com.jspiders.taskapi.data.task;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TaskDto {
    private Long taskId;
    private String title;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;
}
