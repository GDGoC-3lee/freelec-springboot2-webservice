package com.jojoldu.book.springboot.service.like;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.like.Like_Table;
import com.jojoldu.book.springboot.domain.like.LikeRepository;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.service.user.UserService;
import com.jojoldu.book.springboot.web.dto.LikeResponseDto;
import com.jojoldu.book.springboot.web.dto.LikeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostsService postsService;
    private final UserService userService;

    @Transactional
    public Long toggleLike(Long post_id, String username) {

        Posts posts = postsService.findById(post_id).toEntity();
        User user = userService.findByUsername(username).toEntity();

        Like_Table likeEntity = likeRepository.findByPostsAndUser(posts, user)
                .orElse(null);

        if (likeEntity == null) {
            likeEntity = Like_Table.builder()
                    .posts(posts)
                    .user(user)
                    .liked(true)  // 처음에는 false로 생성됨
                    .build();
            likeRepository.save(likeEntity);
        } else {
            likeEntity.update(!likeEntity.isLiked());
        }

        return likeEntity.getLike_id();
    }

    // 좋아요 상태 확인
    public boolean isLiked(Long post_id, String username) {
        Posts post = postsService.findById(post_id).toEntity();
        User user = userService.findByUsername(username).toEntity();
        Like_Table likeEntity = likeRepository.findByPostsAndUser(post, user)
                .orElse(null);
        return likeEntity != null && likeEntity.isLiked();
    }
}
