package com.jspiders.taskapi.data.comments;

import com.jspiders.taskapi.data.users.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "createdAt")
    private String createdAt;
    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUser;
}
