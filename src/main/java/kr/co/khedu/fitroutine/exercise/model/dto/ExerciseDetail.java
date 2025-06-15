package kr.co.khedu.fitroutine.exercise.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class ExerciseDetail {

    private final int exerciseDetailId;
    private final int dailyExercisId;
    private final int exerciseId;


    @Builder
    private ExerciseDetail(final int exerciseDetailId, final int dailyExercisId, final int exerciseId) {
        this.exerciseDetailId = exerciseDetailId;
        this.dailyExercisId = dailyExercisId;
        this.exerciseId = exerciseId;
    }
}
