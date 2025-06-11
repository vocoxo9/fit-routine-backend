package kr.co.khedu.fitroutine.post.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import kr.co.khedu.fitroutine.post.model.dto.PostResponse;
import kr.co.khedu.fitroutine.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public final class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/blogs/{blogId}/posts")
    public ResponseEntity<List<? extends PostResponse>> getPosts(
            @PathVariable long blogId,
            @RequestParam @Min(0) int page,
            @RequestParam @Min(1) @Max(12) int size
    ) {
        return ResponseEntity.ok(postService.getPosts(blogId, page, size));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }
}
