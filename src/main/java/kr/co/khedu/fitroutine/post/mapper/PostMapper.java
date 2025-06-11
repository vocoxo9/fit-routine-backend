package kr.co.khedu.fitroutine.post.mapper;

import kr.co.khedu.fitroutine.post.model.dto.PostCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.PostResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface PostMapper {
    List<? extends PostResponse> findPosts(long blogId, int offset, int size);

    @Nullable PostResponse findPost(long postId);

    void insertPost(long blogId, PostCreateRequest createRequest);
}
