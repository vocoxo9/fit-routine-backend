package kr.co.khedu.fitroutine.blog.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import kr.co.khedu.fitroutine.blog.model.dto.*;
import kr.co.khedu.fitroutine.blog.service.BlogService;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@Validated
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogResponse> getBlog(@PathVariable long blogId) {
        return ResponseEntity.ok(blogService.getBlog(blogId));
    }

    @GetMapping("/me")
    public ResponseEntity<BlogResponse> getMyBlog(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(blogService.getMyBlog(userDetails.getMemberId()));
    }

    @PatchMapping("/me")
    public ResponseEntity<BlogResponse> updateMyBlog(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid BlogUpdateRequest blogUpdateRequest
    ) {
        return ResponseEntity.ok(blogService.updateMyBlog(userDetails.getMemberId(), blogUpdateRequest));
    }

    @GetMapping("/{blogId}/followers")
    public ResponseEntity<List<? extends FollowResponse>> getFollowers(
            @PathVariable long blogId,
            @RequestParam @Min(0) int page,
            @RequestParam @Min(1) @Max(12) int size
    ) {
        return ResponseEntity.ok(blogService.getFollowers(blogId, page, size));
    }

    @GetMapping("/me/followers")
    public ResponseEntity<List<? extends FollowResponse>> getMyFollowers(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam @Min(0) int page,
            @RequestParam @Min(1) @Max(12) int size
    ) {
        return ResponseEntity.ok(blogService.getMyFollowers(userDetails.getMemberId(), page, size));
    }

    @GetMapping("/{blogId}/followers/count")
    public ResponseEntity<FollowCountResponse> getFollowersCount(@PathVariable long blogId) {
        return ResponseEntity.ok(blogService.getFollowersCount(blogId));
    }

    @GetMapping("/me/followers/count")
    public ResponseEntity<FollowCountResponse> getMyFollowersCount(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(blogService.getMyFollowersCount(userDetails.getMemberId()));
    }

    @GetMapping("/{blogId}/followings")
    public ResponseEntity<List<? extends FollowResponse>> getFollowings(
            @PathVariable long blogId,
            @RequestParam @Min(0) int page,
            @RequestParam @Min(1) @Max(12) int size
    ) {
        return ResponseEntity.ok(blogService.getFollowings(blogId, page, size));
    }

    @GetMapping("/me/followings")
    public ResponseEntity<List<? extends FollowResponse>> getMyFollowings(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam @Min(0) int page,
            @RequestParam @Min(1) @Max(12) int size
    ) {
        return ResponseEntity.ok(blogService.getMyFollowings(userDetails.getMemberId(), page, size));
    }

    @GetMapping("/{blogId}/followings/count")
    public ResponseEntity<FollowCountResponse> getFollowingsCount(@PathVariable long blogId) {
        return ResponseEntity.ok(blogService.getFollowingsCount(blogId));
    }

    @GetMapping("/me/followings/count")
    public ResponseEntity<FollowCountResponse> getMyFollowingsCount(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(blogService.getMyFollowingsCount(userDetails.getMemberId()));
    }

    @GetMapping("/{blogId}/follow")
    public ResponseEntity<FollowStatusResponse> checkFollow(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable long blogId
    ) {
        return ResponseEntity.ok(blogService.checkFollow(userDetails.getMemberId(), blogId));
    }

    @PostMapping("/{blogId}/follow")
    public ResponseEntity<Void> followBlog(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable long blogId
    ) {
        blogService.followBlog(userDetails.getMemberId(), blogId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{blogId}/follow")
    public ResponseEntity<Void> unfollowBlog(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable long blogId
    ) {
        blogService.unfollowBlog(userDetails.getMemberId(), blogId);
        return ResponseEntity.noContent().build();
    }
}
