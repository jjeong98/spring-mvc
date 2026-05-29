package com.codeit.mvc.repository;

import com.codeit.mvc.domain.Category;
import com.codeit.mvc.domain.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MemoryPostRepository implements PostRepository {

    // ConcurrentHashMap: 멀티 스레드 환경에서 안전한 HashMap (여러 스레드에서 하나의 Map을 공유할 수 있음)
    private final Map<Long, Post> store = new ConcurrentHashMap<>();
    // 멀티 스레드 환경에서 동기화 없이도 안전하게 변경할 수 있는 long 타입의 변수 AtomicLong
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Post> findById(Long id) {
        // null을 허용하는 Optional 객체를 생성해서 리턴. of()는 null을 허용하지 않습니다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            post.setId(sequence.getAndIncrement());
        }
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public List<Post> findByCategory(Category category) {
        return store.values().stream()
                .filter(post -> post.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByTitleContaining(String title) {
        return store.values().stream()
                .filter(post -> post.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByTitleOrContentContaining(String keyword) {
        return store.values().stream()
                .filter(post -> post.getTitle().contains(keyword)
                        || post.getContent().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public void updateViewCount(Long id) {
        store.get(id).setViewCount();
    }
}
