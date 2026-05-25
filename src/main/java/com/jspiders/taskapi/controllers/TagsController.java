package com.jspiders.taskapi.controllers;

import com.jspiders.taskapi.data.tags.CreateTagRequest;
import com.jspiders.taskapi.services.impl.TagServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagsController {
    private final TagServiceimpl tagServiceimpl;
    @PostMapping
    public ResponseEntity<String> createNewTag(@RequestBody CreateTagRequest createTagRequest){
        return tagServiceimpl.addTag(createTagRequest);
    }
}
