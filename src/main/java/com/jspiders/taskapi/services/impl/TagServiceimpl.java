package com.jspiders.taskapi.services.impl;

import com.jspiders.taskapi.data.tags.CreateTagRequest;
import com.jspiders.taskapi.data.tags.TagRepository;
import com.jspiders.taskapi.data.tags.Tags;
import com.jspiders.taskapi.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Service
public class TagServiceimpl implements TagService {
    private final TagRepository tagRepository;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<String> addTag(CreateTagRequest createTagRequest) {
      //bl
        Tags tag = mapper.convertValue(createTagRequest, Tags.class);
tagRepository.save(tag);
        return ResponseEntity.ok("tag created");
    }
}
