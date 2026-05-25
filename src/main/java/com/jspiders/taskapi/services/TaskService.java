package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.task.AssignTagToTaskResponse;
import com.jspiders.taskapi.data.task.CreateTaskRequest;
import com.jspiders.taskapi.data.task.Task;
import com.jspiders.taskapi.data.task.UpdateTaskRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    ResponseEntity<Task> createTask(CreateTaskRequest createTaskRequest);


    ResponseEntity<List<Task>> getAllTask();

    ResponseEntity<Task> getTaskById(Long taskId);

    ResponseEntity<String> updateTask(UpdateTaskRequest updateTaskRequest);

    ResponseEntity<String> deleteTaskById(Long taskId);

    ResponseEntity<AssignTagToTaskResponse> addTagToTask(Long taskId,Long tagId);


}
