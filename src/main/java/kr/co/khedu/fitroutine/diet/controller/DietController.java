package kr.co.khedu.fitroutine.diet.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import kr.co.khedu.fitroutine.diet.model.dto.MenuCategory;
import kr.co.khedu.fitroutine.diet.model.dto.MenuResponse;
import kr.co.khedu.fitroutine.diet.service.DietService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diets")
@Validated
public class DietController {
    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping("/menus")
    public ResponseEntity<List<? extends MenuResponse>> getMenus(
            @RequestParam(required = false) @Nullable MenuCategory category,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "12") @Min(1) @Max(12) int size
    ) {
        return ResponseEntity.ok(dietService.getMenus(category, page, size));
    }

    @GetMapping("/menus/{menuId}")
    public ResponseEntity<MenuResponse> getMenu(@PathVariable long menuId) {
        return ResponseEntity.ok(dietService.getMenu(menuId));
    }
}
