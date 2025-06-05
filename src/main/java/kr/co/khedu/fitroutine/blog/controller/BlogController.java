package kr.co.khedu.fitroutine.blog.controller;

import kr.co.khedu.fitroutine.blog.model.dto.BlogDetail;
import kr.co.khedu.fitroutine.blog.service.BlogService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public final class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/{memberId}")
    public BlogDetail getBlogDetail(@PathVariable long memberId) {
        // 토큰 서비스 추가 시 수정
        long viewerId = 4;
        return blogService.getBlogDetail(memberId, viewerId);
    }

    @DeleteMapping("/{memberId}/likes")
    public String unlikeBlog(
            @PathVariable long memberId,
            @RequestHeader("Authorization") String token
    ) {
        // 추후 토큰으로 로그인 유저 memberId만 추출
        long viewerId = Integer.parseInt(token); // 추후 변경 예정

        return blogService.unlikeBlog(memberId, viewerId) ? "success" : "failure";
    }

    @PostMapping("/{memberId}/likes")
    public String likeBlog(
            @PathVariable long memberId,
            @RequestHeader("Authorization") String token
    ) {
        // 추후 토큰으로 로그인 유저 memberId만 추출
        long viewerId = Integer.parseInt(token); // 추후 변경 예정

        return blogService.likeBlog(memberId, viewerId)  ? "success" : "failure";
    }

}
