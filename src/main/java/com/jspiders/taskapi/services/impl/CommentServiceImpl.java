package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.comments.Comments;
import com.jspiders.taskapi.data.comments.CommentsRepository;
import com.jspiders.taskapi.data.comments.CreateCommentsRequest;
import com.jspiders.taskapi.data.comments.createCommentsResponse;
import com.jspiders.taskapi.data.users.AppUser;
import com.jspiders.taskapi.data.users.AppUserRepository;
import com.jspiders.taskapi.data.users.AppuserCommentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl {
    private final AppUserRepository appUserRepository;
    private final CommentsRepository commentsRepository;
    private final ObjectMapper mapper;

    public ResponseEntity<createCommentsResponse> createcomments(CreateCommentsRequest createCommentsRequest) {

        log.info("inside createcomments{}", createCommentsRequest);

        AppUser appUser = appUserRepository.findById(createCommentsRequest.getId()).orElseThrow();


        Comments comments = mapper.convertValue(createCommentsRequest, Comments.class);
        comments.setCreatedAt(LocalDate.now().toString());


        Comments savecomments = commentsRepository.save(comments);

        AppuserCommentResponse appuserCommentResponse = mapper.convertValue(appUser, AppuserCommentResponse.class);

        createCommentsResponse createCommentsResponse = mapper.convertValue(savecomments, createCommentsResponse.class);
        createCommentsResponse.setAppuserCommentResponse(appuserCommentResponse);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createCommentsResponse);
    }


}
