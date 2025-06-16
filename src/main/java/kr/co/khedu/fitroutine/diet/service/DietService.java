package kr.co.khedu.fitroutine.diet.service;

import kr.co.khedu.fitroutine.diet.mapper.DietMapper;
import kr.co.khedu.fitroutine.diet.model.dto.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Transactional
public class DietService {
    private final DietMapper dietMapper;

    public DietService(DietMapper dietMapper) {
        this.dietMapper = dietMapper;
    }

    @Transactional(readOnly = true)
    public List<? extends MenuResponse> getMenus(@Nullable MenuCategory category, int page, int size) {
        return dietMapper.selectMenus(category, page * size, size);
    }

    @Transactional(readOnly = true)
    public MenuResponse getMenu(long menuId) {
        MenuResponse menuResponse = dietMapper.selectMenuById(menuId);
        if (menuResponse == null) {
            throw new IllegalArgumentException("메뉴를 찾을 수 없습니다. id=" + menuId);
        }

        return menuResponse;
    }

    public DietResponse generateDiet(int dayRepeat) {
        return new DietResponse(
                IntStream.rangeClosed(1, dayRepeat)
                        .mapToObj(this::generateDay)
                        .toList()
        );
    }

    private DietResponse.Day generateDay(int dayNo) {
        return new DietResponse.Day(
                dayNo,
                Stream.generate(this::generateMeal)
                        .limit(3)
                        .toList()
        );
    }

    private DietResponse.Meal generateMeal() {
        return new DietResponse.Meal(
                Arrays.stream(MenuCategory.values())
                        .map(dietMapper::selectRandomMenuId)
                        .peek(Objects::requireNonNull)
                        .toList()
        );
    }
}
