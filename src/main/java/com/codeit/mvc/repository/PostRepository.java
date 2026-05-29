package com.codeit.mvc.repository;

import com.codeit.mvc.domain.Category;
import com.codeit.mvc.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    // 전체 조회
    List<Post> findAll();

    // id로 게시글 조회
    Optional<Post> findById(Long id);

    // 게시글 등록
    Post save(Post post);

    // 삭제
    void deleteById(Long id);

    // 카테고리로 조회하기
    List<Post> findByCategory(Category category);

    // 제목으로 조회하기
    List<Post> findByTitleContaining(String title);

    // 제목 또는 내용으로 조회하기
    List<Post> findByTitleOrContentContaining(String keyword);

    // 조회수 수정
    void updateViewCount(Long id);

}










