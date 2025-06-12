package kr.co.khedu.fitroutine.post.controller;

import jakarta.validation.Valid;
import kr.co.khedu.fitroutine.post.model.dto.ReplyCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.ReplyResponse;
import kr.co.khedu.fitroutine.post.service.PostReplyService;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class PostReplyController {
    private final PostReplyService postReplyService;

    public PostReplyController(PostReplyService postReplyService) {
        this.postReplyService = postReplyService;
    }

    @GetMapping("/posts/{postId}/replies")
    public ResponseEntity<List<? extends ReplyResponse>> getReplies(@PathVariable long postId) {
        return ResponseEntity.ok(postReplyService.getReplies(postId));
    }

    @GetMapping("/replies/{replyId}")
    public ResponseEntity<ReplyResponse> getReply(@PathVariable long replyId) {
        return ResponseEntity.ok(postReplyService.getReply(replyId));
    }

    @PostMapping("/posts/{postId}/replies")
    public ResponseEntity<ReplyResponse> createReply(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable long postId,
            @RequestBody @Valid ReplyCreateRequest createRequest
    ) {
        return ResponseEntity.ok(
                postReplyService.createReply(
                        postId,
                        userDetails.getMemberId(),
                        createRequest
                )
        );
    }
}
