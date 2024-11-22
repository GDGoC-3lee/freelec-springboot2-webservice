package com.jojoldu.book.springboot.domain.like;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Like extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long like_id;

    @Column(length=500, nullable=false)
    private String title;

    @Builder
    public Like(String title){
        this.title=title;

    }
}
