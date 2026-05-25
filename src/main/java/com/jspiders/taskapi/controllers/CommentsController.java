package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.comments.CreateCommentsRequest;
import com.jspiders.taskapi.data.comments.createCommentsResponse;
import com.jspiders.taskapi.services.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentsController {
    private final CommentServiceImpl commentService;
    @PostMapping
    ResponseEntity<createCommentsResponse> addcomment(@RequestBody CreateCommentsRequest createCommentsRequest){
        log.info("inside addcomment {} ",createCommentsRequest);

        ResponseEntity<createCommentsResponse> response = commentService.createcomments(createCommentsRequest);
        return response;


    }

}
