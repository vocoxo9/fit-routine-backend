package kr.co.khedu.fitroutine.blog.service;

import kr.co.khedu.fitroutine.blog.mapper.BlogMapper;
import kr.co.khedu.fitroutine.blog.model.dto.BlogDetail;
import org.springframework.stereotype.Service;

@Service
public final class BlogService {
    private final BlogMapper blogMapper;

    public BlogService(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    public BlogDetail getBlogDetail(long memberId, long viewerId) {
        BlogDetail blogDetail = blogMapper.getBlogDetail(memberId, viewerId);
        if (blogDetail == null) {
            throw new IllegalStateException("블로그를 찾을 수 없습니다: " + memberId);
        }

        return blogDetail;
    }

    public String unlikeBlog(long memberId, long viewerId) {
        int result = blogMapper.unlikeBlog(memberId, viewerId);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    public String likeBlog(long memberId, long viewerId) {
        int result = blogMapper.likeBlog(memberId, viewerId);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
}
