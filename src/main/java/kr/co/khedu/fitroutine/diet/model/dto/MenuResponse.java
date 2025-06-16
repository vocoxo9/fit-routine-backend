package kr.co.khedu.fitroutine.diet.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class MenuResponse {
    private final long menuId;
    private final String name;
    private final int calorie;
    private final int carbohydrate;
    private final int protein;
    private final int fat;
    private final int sodium;
    private final MenuCategory category;

    @Builder
    private MenuResponse(
            long menuId,
            String name,
            int calorie,
            int carbohydrate,
            int protein,
            int fat,
            int sodium,
            MenuCategory category
    ) {
        this.menuId = menuId;
        this.name = name;
        this.calorie = calorie;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.category = category;
    }
}
