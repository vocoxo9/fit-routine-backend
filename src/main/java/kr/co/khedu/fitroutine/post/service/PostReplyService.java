package kr.co.khedu.fitroutine.post.service;

import kr.co.khedu.fitroutine.post.mapper.PostReplyMapper;
import kr.co.khedu.fitroutine.post.model.dto.ReplyResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostReplyService {
    private final PostReplyMapper postReplyMapper;

    public PostReplyService(PostReplyMapper postReplyMapper) {
        this.postReplyMapper = postReplyMapper;
    }

    public List<? extends ReplyResponse> getReplies(long postId) {
        return postReplyMapper.selectRepliesByPostId(postId);
    }

    public ReplyResponse getReply(long replyId) {
        return postReplyMapper.selectReplyById(replyId);
    }
}
