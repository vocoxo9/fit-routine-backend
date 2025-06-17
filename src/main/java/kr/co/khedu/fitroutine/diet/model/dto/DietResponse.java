package kr.co.khedu.fitroutine.diet.model.dto;

import java.util.List;

public record DietResponse(List<? extends Day> days) {
    public record Day(int dayNo, List<? extends Meal> meals) {
    }

    public record Meal(List<? extends Long> menuIds) {
    }
}
