package kr.co.khedu.fitroutine.post.mapper;

import kr.co.khedu.fitroutine.post.model.dto.ReplyCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.ReplyResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface PostReplyMapper {
    List<? extends ReplyResponse> selectRepliesByPostId(long postId);

    @Nullable ReplyResponse selectReplyById(long replyId);

    int existsReplyByMemberId(long replyId, long memberId);

    void insertReply(long postId, long memberId, ReplyCreateRequest createRequest);
}
