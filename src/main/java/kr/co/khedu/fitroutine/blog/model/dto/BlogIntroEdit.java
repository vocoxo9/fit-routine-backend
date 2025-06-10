package kr.co.khedu.fitroutine.blog.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class BlogIntroEdit {
    private final String intro;

    @Builder
    private BlogIntroEdit(String intro) {
        this.intro = intro;
    }
}
