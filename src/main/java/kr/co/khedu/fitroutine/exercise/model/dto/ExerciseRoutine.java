package kr.co.khedu.fitroutine.exercise.model.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public final class ExerciseRoutine {

    private final long memberId;
    private final Date startedAt;
    private final Date endedAt;
    private final String purpose;
    private final int dayRepeat;
    private final double tdee;
    private final double goalWeight;
    private final List<List<Integer>> exerciseList;

    @Builder
    public ExerciseRoutine(long memberId,
                           Date startedAt,
                           Date endedAt,
                           String purpose,
                           int dayRepeat,
                           double tdee,
                           double goalWeight,
                           List<List<Integer>> exerciseList) {
        this.memberId = memberId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.purpose = purpose;
        this.dayRepeat = dayRepeat;
        this.tdee = tdee;
        this.goalWeight = goalWeight;
        this.exerciseList = exerciseList;
    }

    public ExerciseRoutine(int dayRepeat, List<List<Integer>> exerciseList) {
        this(0, null, null, null, dayRepeat, 0, 0.0, exerciseList);
    }
}
