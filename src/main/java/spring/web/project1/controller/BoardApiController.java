package spring.web.project1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.web.project1.dto.BoardResDto;
import spring.web.project1.dto.BoardSaveDto;
import spring.web.project1.dto.BoardUpdateDto;
import spring.web.project1.entity.Board;
import spring.web.project1.service.BoardService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    @Value("${file.upload-dir}")
    private String filedir;

    private final BoardService boardService;

    @GetMapping(value = "/api/post/getall")
    public List<Board> getall(){
        return boardService.getAll();
    }

//    @PostMapping(value = "/api/post",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Long> saveBoard(@ModelAttribute(value = "key") BoardSaveDto boardSaveDto,
//                                          @RequestPart(value = "file") List<MultipartFile> file) {
//        try {
//            Long boardId = boardService.save(boardSaveDto,file);
//            return ResponseEntity.status(HttpStatus.CREATED).body(boardId);
//        } catch (IOException e) {
//            // 파일 저장에 실패한 경우 예외 처리
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping(value = "/api/post")
    public Long save(@RequestBody BoardSaveDto boardSaveDto) throws Exception {
        return boardService.save(boardSaveDto);
    }

    @GetMapping(value = "api/post/{nno}")
    public BoardResDto getId(@PathVariable Long nno){
        return boardService.findById(nno);
    }

    @PutMapping(value = "api/post/{nno}")
    public Long update(@PathVariable Long nno, @RequestBody BoardUpdateDto boardUpdateDto){
        return boardService.update(nno, boardUpdateDto);
    }

    @DeleteMapping(value = "api/post/{nno}")
    public Long delete(@PathVariable Long nno){
        boardService.delete(nno);
        return nno;
    }
}
