package com.codeit.mvc.dto.response;

import com.codeit.mvc.domain.Category;
import com.codeit.mvc.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
//응답용 dto
//응답에 맞는 요청을하자
@Getter
@Builder
public class PostResponse {


    //DTO의 필드가 entity   와 동일하긴 하지만, 그래도 dto로 변환해서 내리는 것을 추천
    // 예를 들어 날짜를 가공 응답, 이미지 경로 가공 할 필요성도 있음
    private Long id;
    private String title;
    private String content;
    private String author;
    private Category category;
    private int viewCount;
    private String thumbnailPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .category(post.getCategory())
                .viewCount(post.getViewCount())
                .thumbnailPath(post.getThumbnailPath())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
