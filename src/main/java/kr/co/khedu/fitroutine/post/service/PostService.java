package kr.co.khedu.fitroutine.post.service;

import kr.co.khedu.fitroutine.post.mapper.PostMapper;
import kr.co.khedu.fitroutine.post.model.dto.PostCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.PostResponse;
import kr.co.khedu.fitroutine.post.model.dto.PostUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PostService {
    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
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

    public PostResponse createPost(long blogId, PostCreateRequest createRequest) {
        postMapper.insertPost(blogId, createRequest);

        Long postId = createRequest.getPostId();
        if (postId == null) {
            throw new IllegalStateException("포스트를 추가할 수 없습니다.");
        }

        return getPost(postId);
    }

    public PostResponse updatePost(long postId, long memberId, PostUpdateRequest updateRequest) {
        if (postMapper.existsPostByMember(postId, memberId) != 1) {
            throw new IllegalArgumentException("포스트는 본인만 수정할 수 있습니다. id=" + postId);
        }

        if (postMapper.updatePost(postId, updateRequest) != 1) {
            throw new NoSuchElementException("포스트가 존재하지 않습니다. id=" + postId);
        }

        return getPost(postId);
    }

    public void deletePost(long postId, long memberId) {
        if (postMapper.existsPostByMember(postId, memberId) != 1) {
            throw new IllegalArgumentException("포스트는 본인만 수정할 수 있습니다. id=" + postId);
        }

        if (postMapper.deletePost(postId) != 1) {
            throw new NoSuchElementException("포스트가 존재하지 않습니다. id=" + postId);
        }
    }
}
