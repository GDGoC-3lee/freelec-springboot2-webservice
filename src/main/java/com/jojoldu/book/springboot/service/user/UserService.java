package com.jojoldu.book.springboot.service.user;

import com.jojoldu.book.springboot.domain.like.Like_Table;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import com.jojoldu.book.springboot.web.dto.LikeResponseDto;
import com.jojoldu.book.springboot.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto findById(String id){
        Long Id = Long.parseLong(id);
        User entity=userRepository.findById(Id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new UserResponseDto(entity);
    }

    @Transactional
    public UserResponseDto findByUsername(String username){
        User entity=userRepository.findByName(username)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. name="+username));
        return new UserResponseDto(entity);
    }
}
