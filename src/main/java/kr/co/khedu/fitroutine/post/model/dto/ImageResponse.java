package kr.co.khedu.fitroutine.post.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class ImageResponse {
    private final long imageId;
    private final String originName;
    private final String changeName;
    private final long postId;

    @Builder
    private ImageResponse(
            long imageId,
            String originName,
            String changeName,
            long postId
    ) {
        this.imageId = imageId;
        this.originName = originName;
        this.changeName = changeName;
        this.postId = postId;
    }
}
