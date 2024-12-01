package com.jojoldu.book.springboot.domain.like;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like_Table,Long> {
    boolean existsByPostsAndUser(Posts posts, User user);

    // 좋아요 엔티티 조회
    Optional<Like_Table> findByPostsAndUser(Posts posts, User user);
}
