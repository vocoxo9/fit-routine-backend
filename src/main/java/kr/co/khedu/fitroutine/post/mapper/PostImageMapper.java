package kr.co.khedu.fitroutine.post.mapper;

import kr.co.khedu.fitroutine.post.model.dto.ImageCreateRequest;
import kr.co.khedu.fitroutine.post.model.dto.ImageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface PostImageMapper {
    List<? extends ImageResponse> selectImagesByPostId(long postId);

    @Nullable ImageResponse selectImageById(long imageId);

    int existsImageByMemberId(long imageId, long memberId);

    void insertImage(long postId, ImageCreateRequest createRequest);

    int deleteImage(long imageId);
}
