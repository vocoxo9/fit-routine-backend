package kr.co.khedu.fitroutine.blog.controller;

import kr.co.khedu.fitroutine.blog.model.dto.BlogDetail;
import kr.co.khedu.fitroutine.blog.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        long viewerId = 1;

        return blogService.getBlogDetail(memberId, viewerId);
    }
}
