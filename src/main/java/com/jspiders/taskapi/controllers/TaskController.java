package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.task.AssignTagToTaskResponse;
import com.jspiders.taskapi.data.task.CreateTaskRequest;
import com.jspiders.taskapi.data.task.Task;
import com.jspiders.taskapi.data.task.UpdateTaskRequest;
import com.jspiders.taskapi.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1/task")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;


    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
       log.info("createTask(){}",createTaskRequest);

        return taskService.createTask(createTaskRequest);

    }
@GetMapping
    public ResponseEntity<List<Task>> getAllTask() {
        System.out.println("This is TaskServiceimpl -->getAllTask()");
    ResponseEntity<List<Task>> response = taskService.getAllTask();
    return response;

    }
@GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        System.out.println("This is TaskServiceimpl -->getTaskById()");
    ResponseEntity<Task> response = taskService.getTaskById(taskId);
    return response;

    }
@PutMapping
    public ResponseEntity<String> updateTask(UpdateTaskRequest updateTaskRequest) {
        System.out.println("This is TaskServiceimpl -->updateTask()");
    ResponseEntity<String> response = taskService.updateTask(updateTaskRequest);
    return response;

    }
@DeleteMapping
    public ResponseEntity<String> deleteTaskById(Long taskId) {
        System.out.println("This is TaskServiceimpl -->deleteTaskById(;;)");
    ResponseEntity<String> response = taskService.deleteTaskById(taskId);
    return response;
    }


    @PostMapping("/{taskId}/tags/{tagId}")
    public ResponseEntity<AssignTagToTaskResponse> addTagToTask(@PathVariable Long taskId,
                                                                @PathVariable Long tagId){
        return taskService.addTagToTask(taskId,tagId);
    }

}
