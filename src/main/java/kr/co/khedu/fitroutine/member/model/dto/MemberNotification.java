package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberNotification {
    private long noticeId;
    private String category;
    private String nickname;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    private MemberNotification(long noticeId, String category, String nickname, String content, LocalDateTime createdAt) {
        this.noticeId = noticeId;
        this.category = category;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }
}
