package kr.co.khedu.fitroutine.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public final class ImageCreateRequest {
    private @Nullable Long imageId;
    private final String originName;
    private final String changeName;

    @Builder
    private ImageCreateRequest(
            String originName,
            String changeName
    ) {
        this.originName = originName;
        this.changeName = changeName;
    }
}
