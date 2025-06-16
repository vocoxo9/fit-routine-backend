package kr.co.khedu.fitroutine.diet.service;

import kr.co.khedu.fitroutine.diet.mapper.DietMapper;
import kr.co.khedu.fitroutine.diet.model.dto.MenuCategory;
import kr.co.khedu.fitroutine.diet.model.dto.MenuResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
