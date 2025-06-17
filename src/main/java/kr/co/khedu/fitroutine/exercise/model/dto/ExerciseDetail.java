package kr.co.khedu.fitroutine.exercise.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExerciseDetail {

    private final Long exerciseDetailId;
    private final Long dailyExerciseId;
    private final int exerciseId;

    @Builder
    private ExerciseDetail(Long exerciseDetailId, Long dailyExerciseId, int exerciseId){
        this.exerciseDetailId = exerciseDetailId;
        this.dailyExerciseId = dailyExerciseId;
        this.exerciseId = exerciseId;
    }
}
