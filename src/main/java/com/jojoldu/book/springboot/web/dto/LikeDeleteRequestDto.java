package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeDeleteRequestDto {
    private Long like_id;

    public LikeDeleteRequestDto(Long like_id) {
        this.like_id = like_id;
    }
}
