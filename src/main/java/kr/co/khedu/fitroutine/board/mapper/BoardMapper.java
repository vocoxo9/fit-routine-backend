package kr.co.khedu.fitroutine.board.mapper;

import kr.co.khedu.fitroutine.board.model.dto.BoardCreate;
import kr.co.khedu.fitroutine.board.model.dto.BoardDetailForEdit;
import kr.co.khedu.fitroutine.board.model.dto.PopularBoard;
import kr.co.khedu.fitroutine.board.model.vo.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<? extends PopularBoard> getPopularBoardTop3();

    int insertSelectBoard(BoardCreate board);

    int saveBoardImage(String originalFileName, String changedFileName, long boardId);

    BoardDetailForEdit getBoardDetailForEdit(long boardId, int ownerId);

    List<? extends Image> getImagesByBoardId(long boardId);

    int updateBoardDetail(long ownerId, long boardId, String title, String category, String content);

    int deleteImage(long deleteImageId);
}
