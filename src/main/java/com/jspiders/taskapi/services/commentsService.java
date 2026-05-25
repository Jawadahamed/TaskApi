package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.comments.CreateCommentsRequest;
import com.jspiders.taskapi.data.comments.createCommentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface commentsService {
    ResponseEntity<createCommentsResponse> createcomments(CreateCommentsRequest createCommentsRequest);
}
