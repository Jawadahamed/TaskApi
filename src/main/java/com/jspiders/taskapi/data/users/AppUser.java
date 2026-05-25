package com.jspiders.taskapi.data.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jspiders.taskapi.data.comments.Comments;
import com.jspiders.taskapi.data.task.Task;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = "taskList")
@Entity
@Table(name = "appuser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Mysql will create and manage the Id
    private Long userId;

    @Column(name = "name",nullable = false,length = 45)
    private String name;

    @Column(name = "email",nullable = false,length = 44,unique = true)
    private String email;

    @Column(name = "mobile",nullable = false,length = 10,unique = true)
    private String mobile;

    @Column(name = "password",nullable = false,length = 20)
    private String password;

    @Column(name = "isActive",nullable = false)
    private boolean isActive;


    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<Task> taskList;

    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<Comments> comments;


}
