package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.like.Like_Table;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    private User user;

    public UserResponseDto(User entity){
        this.id=entity.getId();
        this.name=entity.getName();
        this.email=entity.getEmail();
        this.picture=entity.getPicture();
        this.role=entity.getRole();
        this.user=entity;
    }
    public User toEntity(){
        return this.user;
    }
}
