package kr.co.khedu.fitroutine.post.controller;

import kr.co.khedu.fitroutine.post.model.dto.ReplyResponse;
import kr.co.khedu.fitroutine.post.service.PostReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
