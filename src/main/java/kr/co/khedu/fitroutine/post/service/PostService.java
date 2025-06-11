package kr.co.khedu.fitroutine.post.service;

import kr.co.khedu.fitroutine.blog.service.BlogService;
import kr.co.khedu.fitroutine.post.mapper.PostMapper;
import kr.co.khedu.fitroutine.post.model.dto.PostCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.PostResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PostService {
    private final PostMapper postMapper;
    private final BlogService blogService;

    public PostService(
            PostMapper postMapper,
            BlogService blogService
    ) {
        this.postMapper = postMapper;
        this.blogService = blogService;
    }

    @Transactional(readOnly = true)
    public List<? extends PostResponse> getPosts(long blogId, int page, int size) {
        return postMapper.selectPostsByBlogId(blogId, page * size, size);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(long postId) {
        PostResponse postResponse = postMapper.selectPostById(postId);
        if (postResponse == null) {
            throw new NoSuchElementException("포스트를 찾을 수 없습니다. id=" + postId);
        }

        return postResponse;
    }

    private PostResponse createPost(long blogId, PostCreateRequest createRequest) {
        postMapper.insertPost(blogId, createRequest);

        Long postId = createRequest.getPostId();
        if (postId == null) {
            throw new IllegalStateException("포스트를 추가할 수 없습니다.");
        }

        return getPost(postId);
    }

    public PostResponse createMyPost(long memberId, PostCreateRequest createRequest) {
        return createPost(blogService.getMyBlogId(memberId), createRequest);
    }
}
