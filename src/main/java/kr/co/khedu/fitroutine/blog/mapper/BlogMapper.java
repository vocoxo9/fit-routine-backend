package kr.co.khedu.fitroutine.blog.mapper;

import kr.co.khedu.fitroutine.blog.model.dto.BlogResponse;
import kr.co.khedu.fitroutine.blog.model.dto.BlogUpdateRequest;
import kr.co.khedu.fitroutine.blog.model.dto.FollowResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Nullable Long findBlogId(long memberId);

    @Nullable BlogResponse findBlog(long blogId);

    int updateBlog(long blogId, BlogUpdateRequest updateRequest);

    int countFollowers(long blogId);

    int countFollowings(long blogId);

    List<? extends FollowResponse> findFollowers(long blogId, int offset, int size);

    List<? extends FollowResponse> findFollowings(long blogId, int offset, int size);

    int insertFollow(long followerId, long followeeId);

    int deleteFollow(long followerId, long followeeId);
}
