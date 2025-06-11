package kr.co.khedu.fitroutine.blog.mapper;

import kr.co.khedu.fitroutine.blog.model.dto.BlogResponse;
import kr.co.khedu.fitroutine.blog.model.dto.BlogUpdateRequest;
import kr.co.khedu.fitroutine.blog.model.dto.BlogSummaryResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface BlogMapper {
    long findBlogId(long memberId);

    @Nullable BlogResponse findBlog(long blogId);

    int updateBlog(long blogId, BlogUpdateRequest updateRequest);

    List<? extends BlogSummaryResponse> findFollowers(long blogId, int offset, int size);

    List<? extends BlogSummaryResponse> findFollowings(long blogId, int offset, int size);

    int insertFollow(long followerId, long followeeId);

    int deleteFollow(long followerId, long followeeId);
}
