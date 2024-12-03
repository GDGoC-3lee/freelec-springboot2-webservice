package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.service.like.LikeService;
import com.jojoldu.book.springboot.service.user.UserService;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final LikeService likeService;
    private final HttpSession httpSession;
    private final UserService userService;

    @PostMapping
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //게시글 목록 반환
    @GetMapping
    public List<PostsListResponseDto> getPostsList() {
        return postsService.findAllDesc();
    }

    //좋아요 상태 확인 ------
    @GetMapping("/{id}/like")
    public boolean getLikeStatus(@PathVariable Long id) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if (sessionUser != null) {
            String username = sessionUser.getName();
            return likeService.isLiked(id, username);
        }
        return false; //로그인 X
    }


@PutMapping("/{id}/like")
public ResponseEntity<Long> toggleLike(@PathVariable Long id) {
    String username = null;
    SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
    if (sessionUser != null) {
        username = sessionUser.getName();
    }

    Long likeId = likeService.toggleLike(id, username);

    // 상태 변경 후 like_id 반환
    return ResponseEntity.ok(likeId);
}


    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }



}