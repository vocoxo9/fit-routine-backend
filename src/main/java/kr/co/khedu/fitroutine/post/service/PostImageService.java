package kr.co.khedu.fitroutine.post.service;

import kr.co.khedu.fitroutine.post.mapper.PostImageMapper;
import kr.co.khedu.fitroutine.post.model.dto.ImageCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.ImageResponse;
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
public class PostImageService {
    private final PostImageMapper postImageMapper;
    private final String uploadDir;

    public PostImageService(
            PostImageMapper postImageMapper,
            @Value("${file.upload-dir}") String uploadDir
    ) {
        this.postImageMapper = postImageMapper;
        this.uploadDir = uploadDir;
    }

    @Transactional(readOnly = true)
    public List<? extends ImageResponse> getImages(long postId) {
        return postImageMapper.selectImagesByPostId(postId);
    }

    @Transactional(readOnly = true)
    public ImageResponse getImage(long imageId) {
        ImageResponse imageResponse = postImageMapper.selectImageById(imageId);
        if (imageResponse == null) {
            throw new NoSuchElementException("이미지를 찾을 수 없습니다. id=" + imageId);
        }

        return imageResponse;
    }

    @Transactional(readOnly = true)
    public boolean isImageOwner(long imageId, UserDetailsImpl userDetails) {
        return postImageMapper.existsImageByMemberId(
                imageId,
                userDetails.getMemberId()
        ) == 1;
    }

    public ImageResponse createImage(long postId, ImageCreateRequest createRequest) {
        postImageMapper.insertImage(postId, createRequest);

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

    public void deleteImage(long imageId) {
        if (postImageMapper.deleteImage(imageId) != 1) {
            throw new NoSuchElementException("이미지가 존재하지 않습니다. id=" + imageId);
        }
    }
}
