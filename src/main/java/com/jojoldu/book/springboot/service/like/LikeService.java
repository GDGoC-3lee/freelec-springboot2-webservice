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
    public final LikeRepository likeRepository;
    private final PostsService postsService;
    private final UserService userService;

    @Transactional
    public Long save(Long post_id, String username){
        if(post_id==null) throw new IllegalArgumentException("post_id is null");
        if(username==null) throw new IllegalArgumentException("username is null");

        //post_id로 Posts 객체 찾기
        Posts posts = postsService.findById(post_id).toEntity();
        //user_id로 Users 객체 찾기
        User user = userService.findByUsername(username).toEntity();

        Like_Table likeEntity = Like_Table.builder().posts(posts).user(user).build();
        return likeRepository.save(likeEntity).getLike_id();
    }

    @Transactional
    public void delete(Long like_id){
        Like_Table likeTable =likeRepository.findById(like_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 좋아요가 없습니다. id="+like_id));

        likeRepository.delete(likeTable);
    }

    @Transactional
    public LikeResponseDto findById(Long id){
        Like_Table entity=likeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 좋아요가 없습니다. id="+id));

        return new LikeResponseDto(entity);
    }
    // 좋아요 상태 확인 --
    public boolean isLiked(Long post_id, String username){
        Posts post = postsService.findById(post_id).toEntity();
        User user = userService.findByUsername(username).toEntity();
        return likeRepository.existsByPostsAndUser(post, user);
    }
}
