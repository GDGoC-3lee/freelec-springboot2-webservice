package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.like.Like;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
//롬복 어노테이션
@Entity
//entity 정의
//jpa 어노테이션
public class Posts extends BaseTimeEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Like> like=new ArrayList<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable=false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author, User user){
        this.title=title;
        this.content=content;
        this.author=author;
        this.user=user;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
