package kr.co.khedu.fitroutine.exercise.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public final class DailyExercise {

    private @Nullable long dailyExerciseId;
    private final long todoId;
    private final int dayNo;

    @Builder
    private DailyExercise(long todoId, int dayNo) {
        this.todoId = todoId;
        this.dayNo = dayNo;
    }
}
