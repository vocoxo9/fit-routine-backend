package kr.co.khedu.fitroutine.blog.service;

import kr.co.khedu.fitroutine.blog.mapper.BlogMapper;
import kr.co.khedu.fitroutine.blog.model.dto.BlogResponse;
import kr.co.khedu.fitroutine.blog.model.dto.BlogUpdateRequest;
import kr.co.khedu.fitroutine.blog.model.dto.BlogSummaryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogService {
    private final BlogMapper blogMapper;

    public BlogService(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Transactional(readOnly = true)
    public BlogResponse getBlog(long blogId) {
        BlogResponse blogResponse = blogMapper.findBlog(blogId);
        if (blogResponse == null) {
            throw new IllegalStateException();
        }

        return blogResponse;
    }

    @Transactional(readOnly = true)
    public BlogResponse getMyBlog(long memberId) {
        return getBlog(blogMapper.findBlogId(memberId));
    }

    private BlogResponse updateBlog(long blogId, BlogUpdateRequest updateRequest) {
        if (blogMapper.updateBlog(blogId, updateRequest) != 1) {
            throw new IllegalStateException();
        }

        return getBlog(blogId);
    }

    public BlogResponse updateMyBlog(long memberId, BlogUpdateRequest updateRequest) {
        return updateBlog(blogMapper.findBlogId(memberId), updateRequest);
    }

    @Transactional(readOnly = true)
    public List<? extends BlogSummaryResponse> getFollowers(long blogId, int page, int size) {
        return blogMapper.findFollowers(blogId, page * size, size);
    }

    @Transactional(readOnly = true)
    public List<? extends BlogSummaryResponse> getMyFollowers(long memberId, int page, int size) {
        return getFollowers(blogMapper.findBlogId(memberId), page, size);
    }

    @Transactional(readOnly = true)
    public List<? extends BlogSummaryResponse> getFollowings(long blogId, int page, int size) {
        return blogMapper.findFollowings(blogId, page * size, size);
    }

    @Transactional(readOnly = true)
    public List<? extends BlogSummaryResponse> getMyFollowings(long memberId, int page, int size) {
        return getFollowings(blogMapper.findBlogId(memberId), page, size);
    }

    public void followBlog(long followerMemberId, long followedBlogId) {
        if (blogMapper.insertFollow(blogMapper.findBlogId(followerMemberId), followedBlogId) != 1) {
            throw new IllegalStateException();
        }
    }

    public void unfollowBlog(long followerMemberId, long followedBlogId) {
        if (blogMapper.deleteFollow(blogMapper.findBlogId(followerMemberId), followedBlogId) != 1) {
            throw new IllegalStateException();
        }
    }
}
