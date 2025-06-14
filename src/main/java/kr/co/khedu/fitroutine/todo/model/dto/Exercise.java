package kr.co.khedu.fitroutine.todo.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Exercise {
    private final String name;
    private final int calorie;

    @Builder
    private Exercise(String name, int calorie) {
        this.name = name;
        this.calorie = calorie;
    }
}
