package kr.co.khedu.fitroutine.blog.mapper;

import kr.co.khedu.fitroutine.blog.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Nullable Long selectBlogIdByMemberId(long memberId);

    @Nullable BlogResponse selectBlogById(long blogId);

    int existsBlogByMemberId(long blogId, long memberId);

    int updateBlog(long blogId, BlogUpdateRequest updateRequest);

    int countFollowersByBlogId(long blogId);

    int countFollowingsByBlogId(long blogId);

    List<? extends FollowResponse> selectFollowersByBlogId(long blogId, int offset, int size);

    List<? extends FollowResponse> selectFollowingsByBlogId(long blogId, int offset, int size);

    int existsFollowRelation(long followerBlogId, long followedBlogId);

    int insertFollowRelation(long followerBlogId, long followedBlogId);

    int deleteFollowRelation(long followerBlogId, long followedBlogId);
}
