package kr.co.khedu.fitroutine.post.mapper;

import kr.co.khedu.fitroutine.post.model.dto.PostCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.PostLikesResponse;
import kr.co.khedu.fitroutine.post.model.dto.PostResponse;
import kr.co.khedu.fitroutine.post.model.dto.PostUpdateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface PostMapper {
    List<? extends PostResponse> selectPostsByBlogId(@Nullable Long blogId, int offset, int size);

    @Nullable PostResponse selectPostById(long postId);

    int existsPostByMemberId(long postId, long memberId);

    void insertPost(long blogId, PostCreateRequest createRequest);

    int updatePost(long postId, PostUpdateRequest updateRequest);

    int deletePost(long postId);

    @Nullable PostLikesResponse selectPostLikes(long memberId, long postId);

    int likePost(long memberId, long postId);

    int unlikePost(long memberId, long postId);
}
