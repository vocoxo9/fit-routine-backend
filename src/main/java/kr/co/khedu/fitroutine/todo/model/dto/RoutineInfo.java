package kr.co.khedu.fitroutine.todo.model.dto;

import jakarta.annotation.Nullable;
import lombok.*;

import java.sql.Date;

@Getter
public final class RoutineInfo {

    private final long memberId;
    private final Date startedAt;
    private final Date endedAt;
    private final String purpose;
    private final String category;
    private final int dayRepeat;
    private @Nullable double tdee;
    private @Nullable double goalWeight;

    @Builder
    public RoutineInfo(long memberId,
                       Date startedAt,
                       Date endedAt,
                       String purpose,
                       String category,
                       int dayRepeat,
                       double tdee,
                       double goalWeight) {
        this.memberId = memberId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.purpose = purpose;
        this.category = category;
        this.dayRepeat = dayRepeat;
        this.tdee = tdee;
        this.goalWeight = goalWeight;
    }

    public RoutineInfo(long memberId,
                       Date startedAt,
                       Date endedAt,
                       String purpose,
                       String category,
                       int dayRepeat) {
        this.memberId = memberId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.purpose = purpose;
        this.category = category;
        this.dayRepeat = dayRepeat;
    }
}
