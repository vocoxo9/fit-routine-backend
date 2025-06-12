package kr.co.khedu.fitroutine.post.service;

import kr.co.khedu.fitroutine.post.mapper.PostImageMapper;
import kr.co.khedu.fitroutine.post.model.dto.ImageCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.ImageResponse;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import kr.co.khedu.fitroutine.storage.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PostImageService {
    private final PostImageMapper postImageMapper;
    private final FileStorageService fileStorageService;

    public PostImageService(
            PostImageMapper postImageMapper,
            FileStorageService fileStorageService
    ) {
        this.postImageMapper = postImageMapper;
        this.fileStorageService = fileStorageService;
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

    public ImageResponse createImage(long postId, MultipartFile multipartFile) {
        if (multipartFile.getOriginalFilename() == null) {
            throw new IllegalArgumentException("파일 이름이 유효하지 않습니다.");
        }

        ImageCreateRequest createRequest = ImageCreateRequest.builder()
                .originName(
                        multipartFile.getOriginalFilename()
                )
                .changeName(
                        fileStorageService.store(multipartFile)
                )
                .build();

        postImageMapper.insertImage(postId, createRequest);

        Long imageId = createRequest.getImageId();
        if (imageId == null) {
            throw new IllegalStateException("파일 행을 추가할 수 없습니다.");
        }

        return getImage(createRequest.getImageId());
    }

    public void deleteImage(long imageId) {
        fileStorageService.delete(getImage(imageId).getChangeName());

        if (postImageMapper.deleteImage(imageId) != 1) {
            throw new NoSuchElementException("이미지가 존재하지 않습니다. id=" + imageId);
        }
    }
}
