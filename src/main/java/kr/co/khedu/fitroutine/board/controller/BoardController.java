package kr.co.khedu.fitroutine.board.controller;

import kr.co.khedu.fitroutine.board.model.dto.BoardDetailForEdit;
import kr.co.khedu.fitroutine.board.model.dto.BoardDetailWithLike;
import kr.co.khedu.fitroutine.board.model.dto.PopularBoard;
import kr.co.khedu.fitroutine.board.service.BoardService;
import kr.co.khedu.fitroutine.board.utils.ImageFileUtil;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/boards")
public final class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/popular")
    public List<? extends PopularBoard> getPopularBoardTop3() {
        return boardService.getPopularBoardTop3();
    }

    @PostMapping()
    public ResponseEntity<String> createBoard(
            @RequestHeader("Authorization") String token,
            @RequestPart("title") String title,
            @RequestPart("category") String category,
            @RequestPart("content") String content,
            @RequestPart(value = "images") MultipartFile[] images
    ) {
        // 추후 토큰에서 추출하도록 변경
        int memberId = Integer.parseInt(token);

        long boardId = boardService.saveBoardDetail(title, category, content, memberId);
        if (boardId < 0) {
            return ResponseEntity.status(400).body("failure");
        }

        for (MultipartFile image : images) {
            String changedFileName = ImageFileUtil.saveFile(image);

            boolean result = boardService.saveBoardImage(image.getOriginalFilename(), changedFileName, boardId);
            if (!result) {
                return ResponseEntity.status(400).body("failure");
            }
        }

        return ResponseEntity.status(200).body("success");
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Object> getBoardDetail(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable long boardId,
            @RequestParam(value = "includeLike", required = false, defaultValue = "false") boolean includeLike
    ) {
        if (includeLike) {   // 게시물 상세 정보 페이지
            BoardDetailWithLike boardDetailWithLike = boardService.getBoardDetailWithLike(userDetails.getMemberId(), boardId);

            return ResponseEntity.ok(boardDetailWithLike);
        }

        BoardDetailForEdit result = boardService.getBoardDetailForEdit(boardId, userDetails.getMemberId());

        if (result == null) {
            return ResponseEntity.status(404).body("해당 게시글을 찾을 수 없습니다.");
        }

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart("title") String title,
            @RequestPart("category") String category,
            @RequestPart("content") String content,
            @RequestPart(value = "images") MultipartFile[] images,
            @RequestParam(value = "deleteImageIds", required = false) @Nullable long[] deleteImageIds,
            @PathVariable long boardId
    ) {
        boolean result = boardService.updateBoardDetail(userDetails.getMemberId(), boardId, title, category, content, images, deleteImageIds);

        return result ?
                ResponseEntity.status(200).body("success") :
                ResponseEntity.status(400).body("failure");
    }

}
