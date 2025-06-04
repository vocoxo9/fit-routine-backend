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
        BlogDetail blogbuild = blogMapper.getBlogDetail(memberId, viewerId);
        if (blogbuild == null) {
            throw new IllegalStateException("블로그를 찾을 수 없습니다: " + memberId);
        }

        return BlogDetail.builder()
                .introduce(blogbuild.getIntroduce())
                .nickname(blogbuild.getNickname())
                .grade(blogbuild.getGrade())
                .liked(blogbuild.isLiked())
                .likeCount(blogbuild.getLikeCount())
                .gender(blogbuild.getGender().equals("M")?"male":"female")
                .memberId(blogbuild.getMemberId())
                .build();
    }
}
