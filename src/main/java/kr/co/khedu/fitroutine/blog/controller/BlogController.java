package kr.co.khedu.fitroutine.blog.controller;

import kr.co.khedu.fitroutine.blog.model.dto.BlogIntroEdit;
import kr.co.khedu.fitroutine.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public final class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<?> getBlogDetail(@PathVariable long blogId) {
        long memberId = 4; // 추후 변경 예정

        return ResponseEntity.ok(blogService.getBlogDetail(blogId, memberId));
    }

    @PostMapping("/{blogId}/likes")
    public ResponseEntity<?> likeBlog(@PathVariable long blogId) {
        long memberId = 1; // 추후 변경 예정

        blogService.likeBlog(blogId, memberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{blogId}/likes")
    public ResponseEntity<?> unlikeBlog(@PathVariable long blogId) {
        long memberId = 1; // 추후 변경 예정

        blogService.unlikeBlog(blogId, memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<?> updateIntroduce(
            @PathVariable long blogId,
            @RequestBody BlogIntroEdit introEdit
    ) {
        long memberId = 1; // 추후 변경 예정

        blogService.updateBlogIntro(blogId, memberId, introEdit);
        return ResponseEntity.noContent().build();
    }
}
