package com.jojoldu.book.springboot.domain.like;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Like_Table extends BaseTimeEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long like_id;

    @Builder
    public Like_Table(Posts posts, User user){
        this.posts=posts;
        this.user=user;
    }

    public void update(Posts posts, User user){
        this.posts=posts;
        this.user = user;
    }
}

