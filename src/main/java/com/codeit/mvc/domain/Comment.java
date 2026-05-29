package com.codeit.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Setter
    private Long id;
    private Long postId;
    private String content;
    private String author;
    private LocalDateTime createdAt;

}
