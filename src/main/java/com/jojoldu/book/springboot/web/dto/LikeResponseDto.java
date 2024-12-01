package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.like.Like_Table;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

@Getter
public class LikeResponseDto {
    private Long like_id;
    private Posts posts;
    private User user;

    public LikeResponseDto(Like_Table entity){
        this.like_id=entity.getLike_id();
        this.user=entity.getUser();
        this.posts=entity.getPosts();
    }
}
