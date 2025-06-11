package kr.co.khedu.fitroutine.post.service;

import kr.co.khedu.fitroutine.post.mapper.PostMapper;
import kr.co.khedu.fitroutine.post.model.dto.PostResponse;
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
        return postMapper.findPosts(blogId, page * size, size);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(long postId) {
        PostResponse postResponse = postMapper.findPost(postId);
        if (postResponse == null) {
            throw new NoSuchElementException("포스트를 찾을 수 없습니다. id=" + postId);
        }

        return postResponse;
    }
}
