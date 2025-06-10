package kr.co.khedu.fitroutine.blog.service;

import kr.co.khedu.fitroutine.blog.mapper.BlogMapper;
import kr.co.khedu.fitroutine.blog.model.dto.BlogDetail;
import kr.co.khedu.fitroutine.blog.model.dto.BlogIntroEdit;
import kr.co.khedu.fitroutine.member.mapper.MemberMapper;
import kr.co.khedu.fitroutine.member.model.vo.Member;
import org.springframework.stereotype.Service;

@Service
public final class BlogService {
    private final BlogMapper blogMapper;
    private final MemberMapper memberMapper;

    public BlogService(BlogMapper blogMapper, MemberMapper memberMapper) {
        this.blogMapper = blogMapper;
        this.memberMapper = memberMapper;
    }

    public BlogDetail getBlogDetail(long blogId, long viewerId) {
        BlogDetail blogDetail = blogMapper.getBlogDetail(blogId, viewerId);
        if (blogDetail == null) {
            throw new IllegalStateException("블로그를 찾을 수 없습니다: " + blogId);
        }

        return blogDetail;
    }

    public void likeBlog(long blogId, long viewerId) {
        if (blogMapper.likeBlog(memberMapper.findMemberByBlogId(blogId).getMemberId(), viewerId) <= 0) {
            throw new IllegalStateException("좋아요 행을 삽입할 수 없습니다.");
        }
    }

    public void unlikeBlog(long blogId, long viewerId) {
        if (blogMapper.unlikeBlog(memberMapper.findMemberByBlogId(blogId).getMemberId(), viewerId) <= 0) {
            throw new IllegalStateException("좋아요 행을 제거할 수 없습니다.");
        }
    }

    public void updateBlogIntro(long blogId, long memberId, BlogIntroEdit introEdit) {
        if (blogMapper.updateBlogIntro(blogId, memberId, introEdit.getIntro()) <= 0) {
            throw new IllegalStateException("Introduce를 갱신할 수 없습니다.");
        }
    }
}
