package com.jspiders.taskapi.data.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jspiders.taskapi.data.tags.Tags;
import com.jspiders.taskapi.data.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "task")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
@Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "status" ,nullable = false)
    private String status;
    @Column(name = "createdAt" ,nullable = false)
    private String createdAt;
    @Column(name = "updatedAt" ,nullable = false)
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUser;

    @ManyToMany
    @JoinTable(name = "task_tags_ids",joinColumns = @JoinColumn(name = "taskId")
            ,inverseJoinColumns = @JoinColumn(name = "tagId"))
    private Set<Tags> tags=new HashSet<>();//NullpointerException

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
