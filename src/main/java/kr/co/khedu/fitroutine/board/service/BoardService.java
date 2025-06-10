package kr.co.khedu.fitroutine.board.service;

import kr.co.khedu.fitroutine.board.mapper.BoardMapper;
import kr.co.khedu.fitroutine.board.model.dto.BoardCreate;
import kr.co.khedu.fitroutine.board.model.dto.BoardDetailForEdit;
import kr.co.khedu.fitroutine.board.model.dto.BoardDetailWithLike;
import kr.co.khedu.fitroutine.board.model.dto.PopularBoard;
import kr.co.khedu.fitroutine.board.model.vo.Image;
import kr.co.khedu.fitroutine.board.utils.ImageFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public final class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<? extends PopularBoard> getPopularBoardTop3() {
        return boardMapper.getPopularBoardTop3();
    }

    public long saveBoardDetail(String title, String category, String content, int memberId) {
        BoardCreate board = BoardCreate.builder()
                .title(title)
                .category(category)
                .content(content)
                .memberId(memberId)
                .build();

        int rows = boardMapper.insertSelectBoard(board); // insert + selectKey로 board.id 세팅됨

        return rows > 0 ? board.getBoardId() : -1L;
    }

    public boolean saveBoardImage(String originalFileName , String changedFileName, long boardId) {
        return boardMapper.saveBoardImage(originalFileName, changedFileName, boardId) > 0;
    }

    public BoardDetailForEdit getBoardDetailForEdit(long boardId, long ownerId) {

        BoardDetailForEdit boardDetailForEdit = boardMapper.getBoardDetailForEdit(boardId, ownerId);

        if(boardDetailForEdit != null) {
            List<? extends Image> images = boardMapper.getImagesByBoardId(boardId);
            boardDetailForEdit.setImages(images);
        }

        return boardDetailForEdit;
    }

    public boolean updateBoardDetail(long ownerId, long boardId, String title, String category,
                                     String content, MultipartFile[] images, long[] deleteImageIds) {

        int result = boardMapper.updateBoardDetail(ownerId, boardId, title, category, content);

        if(result <= 0) {
            return false;
        }

        for (MultipartFile image : images) {
            String changedFileName = ImageFileUtil.saveFile(image);

            result = boardMapper.saveBoardImage(image.getOriginalFilename(), changedFileName, boardId);
            if (result <= 0) {
                return false;
            }
        }

        for(long deleteImageId : deleteImageIds) {
            result = boardMapper.deleteImage(deleteImageId);
            if (result <= 0) {
                return false;
            }
        }
        return true;
    }

    public BoardDetailWithLike getBoardDetailWithLike(long viewerId, long boardId) {

        BoardDetailWithLike boardDetailWithLike = boardMapper.getBoardDetailWithLike(viewerId, boardId);

        List<? extends Image> images = boardMapper.getImagesByBoardId(boardId);
        boardDetailWithLike.setImages(images);

        return boardDetailWithLike;
    }
}
