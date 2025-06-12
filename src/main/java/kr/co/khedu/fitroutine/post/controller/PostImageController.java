package kr.co.khedu.fitroutine.post.controller;

import kr.co.khedu.fitroutine.post.model.dto.ImageResponse;
import kr.co.khedu.fitroutine.post.service.PostImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Validated
public class PostImageController {
    private final PostImageService postService;

    public PostImageController(PostImageService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{postId}/images")
    public ResponseEntity<List<? extends ImageResponse>> getImages(@PathVariable long postId) {
        return ResponseEntity.ok(postService.getImages(postId));
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<ImageResponse> getImage(@PathVariable long imageId) {
        return ResponseEntity.ok(postService.getImage(imageId));
    }

    @PreAuthorize("@postService.isPostOwner(#postId, principal)")
    @PostMapping("/posts/{postId}/images")
    public ResponseEntity<ImageResponse> createImage(
            @PathVariable long postId,
            @RequestPart MultipartFile multipartFile
    ) {
        return ResponseEntity.ok(postService.createImage(postId, multipartFile));
    }

    @PreAuthorize("@postImageService.isImageOwner(#imageId, principal)")
    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable long imageId) {
        postService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}
