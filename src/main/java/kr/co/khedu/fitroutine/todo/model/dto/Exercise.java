package kr.co.khedu.fitroutine.todo.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Exercise {
    private final String name;
    private final double met;

    @Builder
    private Exercise(String name, double met) {
        this.name = name;
        this.met = met;
    }
}
