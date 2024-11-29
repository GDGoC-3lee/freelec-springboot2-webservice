package com.jojoldu.book.springboot.domain.like;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like_Table,Long> {
    boolean existsByPostsAndUser(Posts posts, User user);
}
