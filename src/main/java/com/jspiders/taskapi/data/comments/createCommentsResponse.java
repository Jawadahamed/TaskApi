package com.jspiders.taskapi.data.comments;


import com.jspiders.taskapi.data.users.AppuserCommentResponse;
import lombok.Data;

@Data
public class createCommentsResponse {
    private Long id;
    private String text;
    private String createdAt;
    private AppuserCommentResponse appuserCommentResponse;
}
