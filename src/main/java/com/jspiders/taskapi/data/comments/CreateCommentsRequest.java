package com.jspiders.taskapi.data.comments;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CreateCommentsRequest {
    private Long Id;
    private String text;
}
