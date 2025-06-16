package kr.co.khedu.fitroutine.diet.mapper;

import kr.co.khedu.fitroutine.diet.model.dto.MenuCategory;
import kr.co.khedu.fitroutine.diet.model.dto.MenuResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface DietMapper {
    List<? extends MenuResponse> selectMenus(@Nullable MenuCategory category, int offset, int size);
}
