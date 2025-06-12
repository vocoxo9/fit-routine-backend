package kr.co.khedu.fitroutine.todo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class RoutineMvpTOP3 {
    private final String nickname;
    private final String count;

    @Builder
    private RoutineMvpTOP3(String nickname, String count) {
        this.nickname = nickname;
        this.count = count;
    }
}
