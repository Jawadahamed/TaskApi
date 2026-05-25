package com.jspiders.taskapi.services;

import com.jspiders.taskapi.data.tags.CreateTagRequest;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    ResponseEntity<String> addTag(CreateTagRequest createTagRequest);
}
