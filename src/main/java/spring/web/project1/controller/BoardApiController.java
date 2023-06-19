package spring.web.project1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.web.project1.dto.BoardResDto;
import spring.web.project1.dto.BoardSaveDto;
import spring.web.project1.dto.BoardUpdateDto;
import spring.web.project1.entity.Board;
import spring.web.project1.service.BoardService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping(value = "/api/post/getall")
    public List<Board> getall(){
        return boardService.getAll();
    }

//    @PostMapping(value = "/api/post")
//    public Long save(@RequestPart(value = "key")BoardSaveDto boardSaveDto, @RequestPart MultipartFile file) throws Exception {
//        return boardService.save(boardSaveDto,file);
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
