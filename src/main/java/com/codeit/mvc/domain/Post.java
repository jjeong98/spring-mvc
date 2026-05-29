package com.codeit.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Entity: DB와 Java 객체를 연결해 주는 가장 핵심적 개념
// DB 테이블과 1:1로 매칭되는 필드를 선언
@Getter
//@Setter <- Entity에서 Setter는 권장하지 않습니다. 필요한 필드에만 선택적으로 생성합니다.
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Category category;
    private int viewCount;
    private String thumbnailPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
