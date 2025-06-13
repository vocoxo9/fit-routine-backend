package kr.co.khedu.fitroutine.post.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public final class ReplyCreateRequest {
    @Null
    private @Nullable Long replyId;

    private final @Nullable Long parentId;

    @NotBlank
    @Size(max = 1500)
    private final String content;

    @Builder
    private ReplyCreateRequest(
            @Nullable Long parentId,
            String content
    ) {
        this.parentId = parentId;
        this.content = content;
    }
}
