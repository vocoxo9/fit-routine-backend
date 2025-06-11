package kr.co.khedu.fitroutine.post.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import kr.co.khedu.fitroutine.post.model.dto.PostCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.PostResponse;
import kr.co.khedu.fitroutine.post.service.PostService;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class PostController {
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

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid PostCreateRequest createRequest
    ) {
        return ResponseEntity.ok(postService.createPost(userDetails.getMemberId(), createRequest));
    }
}
