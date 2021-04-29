package com.example.performance.dto;

import java.util.List;

public interface PostDto {

    Long getId();

    String getTitle();

    List<CommentDto> getComments();

    interface CommentDto {

        Long getId();

        String getText();
    }
}
