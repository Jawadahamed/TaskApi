package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.tags.TagRepository;
import com.jspiders.taskapi.data.tags.Tags;
import com.jspiders.taskapi.data.task.*;
import com.jspiders.taskapi.data.users.AppUser;
import com.jspiders.taskapi.data.users.AppUserRepository;
import com.jspiders.taskapi.errors.InvalidTitleException;
import com.jspiders.taskapi.services.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ObjectMapper mapper;
    private final AppUserRepository appUserRepository;
    private final TagRepository tagRepository;


    @Override
    public ResponseEntity<Task> createTask(CreateTaskRequest createTaskRequest) {
        log.info("inside createTask {}",createTaskRequest);

        //validate the userId if not present Throw NoSuchElementFoundException
        AppUser appUser = appUserRepository.findById(createTaskRequest.getUserId()).orElseThrow();

        //Convert createTaskRequest to Task Entity
        Task task = mapper.convertValue(createTaskRequest, Task.class);

        //set created and updated Dates
        task.setCreatedAt(LocalDate.now().toString());
        task.setUpdatedAt(LocalDate.now().toString());

         task.setAppUser(appUser);
        //save the task to db
       taskRepository.save(task);
//log.info("saved {}",tasksaved);

//return the response with savedTask
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(task);


    }

    @Override
    public ResponseEntity<List<Task>> getAllTask() {
        System.out.println("This is TaskServiceimpl -->getAllTask()");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);

    }

    @Override
    public ResponseEntity<Task> getTaskById(Long taskId) {
        System.out.println("This is TaskServiceimpl -->getTaskById()");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);

    }

    @Override
    public ResponseEntity<String> updateTask(UpdateTaskRequest updateTaskRequest) {
        System.out.println("This is TaskServiceimpl -->updateTask()");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Task Updated");

    }

    @Override
    public ResponseEntity<String> deleteTaskById(Long taskId) {
        System.out.println("This is TaskServiceimpl -->deleteTaskById()");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Task deleted by Id");
    }


    //validate
    private void validateTitle(CreateTaskRequest createTaskRequest){
        if(createTaskRequest.getTitle() != null &&createTaskRequest.getTitle().length()<3){
            InvalidTitleException ex= new InvalidTitleException("! Invalid Title");
            throw ex;
        }
    }

    private void validateDescreption(CreateTaskRequest createTaskRequest){
        if(createTaskRequest.getDescription() != null &&createTaskRequest.getDescription().length()<5){
            InvalidTitleException ex= new InvalidTitleException("! Invalid Title");
            throw ex;
        }
    }


    @Override
    @Transactional
    public ResponseEntity<AssignTagToTaskResponse> addTagToTask(Long taskId, Long tagId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        log.info("task{}",task);

            Set<Tags> tags;         //Set Hashset--> WE USE TO STORE THE TASK'S--> KA TAG(STORE)

       tags = task.getTags();           // WE ARE GETTING TASK--> KE TAG

        Tags tag = tagRepository.findById(tagId).orElseThrow();
        tags.add(tag);
        task.setTags(tags);

//        int x;
//       x=10;
        

taskRepository.save(task);

AssignTagToTaskResponse response=new AssignTagToTaskResponse();
response.setTaskId(task.getTaskId());
response.setTitle(task.getTitle());
response.setTags(task.getTags());


        return ResponseEntity.ok(response);
    }
}
