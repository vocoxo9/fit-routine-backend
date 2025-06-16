package kr.co.khedu.fitroutine.todo.model.dto;

import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutineList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class RoutineUpdateResponse {

    private final RoutineInfo routineInfo;
    private final ExerciseRoutineList exerciseRoutineList;

    @Builder
    private RoutineUpdateResponse(RoutineInfo routineInfo, ExerciseRoutineList exerciseRoutineList) {
        this.routineInfo = routineInfo;
        this.exerciseRoutineList = exerciseRoutineList;
    }
}
