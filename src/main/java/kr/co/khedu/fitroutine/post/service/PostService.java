package kr.co.khedu.fitroutine.post.service;

import kr.co.khedu.fitroutine.post.mapper.PostMapper;
import kr.co.khedu.fitroutine.post.model.dto.*;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class PostService {
    private final PostMapper postMapper;
    private final String uploadDir;

    public PostService(
            PostMapper postMapper,
            @Value("${file.upload-dir}") String uploadDir
    ) {
        this.postMapper = postMapper;
        this.uploadDir = uploadDir;
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

    @Transactional(readOnly = true)
    public boolean isPostOwner(long postId, UserDetailsImpl userDetails) {
        return postMapper.existsPostByMemberId(
                postId,
                userDetails.getMemberId()
        ) == 1;
    }

    public PostResponse createPost(long blogId, PostCreateRequest createRequest) {
        postMapper.insertPost(blogId, createRequest);

        Long postId = createRequest.getPostId();
        if (postId == null) {
            throw new IllegalStateException("포스트를 추가할 수 없습니다.");
        }

        return getPost(postId);
    }

    public PostResponse updatePost(long postId, PostUpdateRequest updateRequest) {
        if (postMapper.updatePost(postId, updateRequest) != 1) {
            throw new NoSuchElementException("포스트가 존재하지 않습니다. id=" + postId);
        }

        return getPost(postId);
    }

    public void deletePost(long postId) {
        if (postMapper.deletePost(postId) != 1) {
            throw new NoSuchElementException("포스트가 존재하지 않습니다. id=" + postId);
        }
    }

    @Transactional(readOnly = true)
    public List<? extends ImageResponse> getImages(long postId) {
        return postMapper.selectImagesByPostId(postId);
    }

    @Transactional(readOnly = true)
    public ImageResponse getImage(long imageId) {
        ImageResponse imageResponse = postMapper.selectImageById(imageId);
        if (imageResponse == null) {
            throw new NoSuchElementException("이미지를 찾을 수 없습니다. id=" + imageId);
        }

        return imageResponse;
    }

    @Transactional(readOnly = true)
    public boolean isImageOwner(long imageId, UserDetailsImpl userDetails) {
        return postMapper.existsImageByMemberId(
                imageId,
                userDetails.getMemberId()
        ) == 1;
    }

    public ImageResponse createImage(long postId, ImageCreateRequest createRequest) {
        postMapper.insertImage(postId, createRequest);

        Long imageId = createRequest.getImageId();
        if (imageId == null) {
            throw new IllegalStateException("파일 행을 추가할 수 없습니다.");
        }

        return getImage(createRequest.getImageId());
    }

    public ImageCreateRequest toCreateRequest(MultipartFile multipartFile) {
        if (multipartFile.getOriginalFilename() == null) {
            throw new IllegalStateException("파일 이름이 유효하지 않습니다.");
        }

        String originName = multipartFile.getOriginalFilename();
        String changeName = UUID.randomUUID() + "." + FilenameUtils.getExtension(originName);

        File destination = new File(uploadDir, changeName);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), destination);
        } catch (IOException exception) {
            throw new RuntimeException("파일 저장에 실패했습니다.", exception);
        }

        return ImageCreateRequest.builder()
                .originName(originName)
                .changeName(changeName)
                .build();
    }
}
