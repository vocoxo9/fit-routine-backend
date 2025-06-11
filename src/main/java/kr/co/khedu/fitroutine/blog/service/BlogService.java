package kr.co.khedu.fitroutine.blog.service;

import kr.co.khedu.fitroutine.blog.mapper.BlogMapper;
import kr.co.khedu.fitroutine.blog.model.dto.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BlogService {
    private final BlogMapper blogMapper;

    public BlogService(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Transactional(readOnly = true)
    public long getMyBlogId(long memberId) {
        Long blogId = blogMapper.selectBlogIdByMemberId(memberId);
        if (blogId == null) {
            throw new NoSuchElementException("멤버에 해당하는 블로그를 찾을 수 없습니다. id=" + memberId);
        }

        return blogId;
    }

    @Transactional(readOnly = true)
    public BlogResponse getBlog(long blogId) {
        BlogResponse blogResponse = blogMapper.selectBlogById(blogId);
        if (blogResponse == null) {
            throw new NoSuchElementException("블로그를 찾을 수 없습니다. id=" + blogId);
        }

        return blogResponse;
    }

    @Transactional(readOnly = true)
    public BlogResponse getMyBlog(long memberId) {
        return getBlog(getMyBlogId(memberId));
    }

    protected BlogResponse updateBlog(long blogId, BlogUpdateRequest updateRequest) {
        if (updateRequest.getIntroduce() == null) {
            throw new IllegalArgumentException("블로그에 수정할 내용이 없습니다. id=" + blogId);
        }

        if (blogMapper.updateBlog(blogId, updateRequest) != 1) {
            throw new DataIntegrityViolationException("블로그 수정 대상이 없습니다. id=" + blogId);
        }

        return getBlog(blogId);
    }

    public BlogResponse updateMyBlog(long memberId, BlogUpdateRequest updateRequest) {
        return updateBlog(getMyBlogId(memberId), updateRequest);
    }

    @Transactional(readOnly = true)
    public FollowCountResponse getFollowersCount(long blogId) {
        return FollowCountResponse.builder()
                .count(blogMapper.countFollowersByBlogId(blogId))
                .build();
    }

    @Transactional(readOnly = true)
    public FollowCountResponse getMyFollowersCount(long memberId) {
        return getFollowersCount(getMyBlogId(memberId));
    }

    @Transactional(readOnly = true)
    public FollowCountResponse getFollowingsCount(long blogId) {
        return FollowCountResponse.builder()
                .count(blogMapper.countFollowingsByBlogId(blogId))
                .build();
    }

    @Transactional(readOnly = true)
    public FollowCountResponse getMyFollowingsCount(long memberId) {
        return getFollowingsCount(getMyBlogId(memberId));
    }

    @Transactional(readOnly = true)
    public List<? extends FollowResponse> getFollowers(long blogId, int page, int size) {
        return blogMapper.selectFollowersByBlogId(blogId, page * size, size);
    }

    @Transactional(readOnly = true)
    public List<? extends FollowResponse> getMyFollowers(long memberId, int page, int size) {
        return getFollowers(getMyBlogId(memberId), page, size);
    }

    @Transactional(readOnly = true)
    public List<? extends FollowResponse> getFollowings(long blogId, int page, int size) {
        return blogMapper.selectFollowingsByBlogId(blogId, page * size, size);
    }

    @Transactional(readOnly = true)
    public List<? extends FollowResponse> getMyFollowings(long memberId, int page, int size) {
        return getFollowings(getMyBlogId(memberId), page, size);
    }

    @Transactional(readOnly = true)
    public FollowStatusResponse checkFollow(long followerMemberId, long followedBlogId) {
        return FollowStatusResponse.builder()
                .followed(blogMapper.existsFollowRelation(getMyBlogId(followerMemberId), followedBlogId) > 0)
                .build();
    }

    public void followBlog(long followerMemberId, long followedBlogId) {
        long followerBlogId = getMyBlogId(followerMemberId);
        if (followerBlogId == followedBlogId) {
            throw new IllegalArgumentException("자신의 블로그는 팔로우할 수 없습니다. id=" + followedBlogId);
        }

        if (blogMapper.insertFollowRelation(followerBlogId, followedBlogId) != 1) {
            throw new IllegalStateException("이미 팔로우한 블로그입니다. id=" + followedBlogId);
        }
    }

    public void unfollowBlog(long followerMemberId, long followedBlogId) {
        long followerBlogId = getMyBlogId(followerMemberId);
        if (blogMapper.deleteFollowRelation(followerBlogId, followedBlogId) != 1) {
            throw new NoSuchElementException("팔로우 관계가 존재하지 않습니다. follower=" + followerBlogId + ", followed=" + followedBlogId);
        }
    }
}
