package kr.co.khedu.fitroutine.todo.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodayRoutine {
    private String routine;
    private String category;

    public TodayRoutine(String routine, String category) {
        this.routine = routine;
        this.category = category;
    }
}
