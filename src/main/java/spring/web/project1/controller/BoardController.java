package spring.web.project1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.web.project1.dto.BoardResDto;
import spring.web.project1.dto.CommentResDto;
import spring.web.project1.entity.Board;
import spring.web.project1.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/")
    public String main(){
        return "main";
    }


    @GetMapping(value = "/board")
    public String board(Model model, @PageableDefault(page = 0, size = 1, sort = "nno", direction = Sort.Direction.DESC)
    Pageable pageable, String searchTitle)
    {
        Page<Board> boardList = null;
        if(searchTitle == null){
            boardList = boardService.getList(pageable);
        }else {
            boardList = boardService.search(searchTitle, pageable);
        }


        int nowPage = boardList.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage +5, boardList.getTotalPages());

        model.addAttribute("boardList",boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/board";
    }

    @GetMapping(value = "/board/post/{nno}")
    public String boardUpdate(@PathVariable Long nno, Model model){
        BoardResDto resDto = boardService.findById(nno);
        model.addAttribute("board", resDto);

        return "/board/update";
    }

    @GetMapping(value = "/board/post/view/{nno}")
    public String boardDtl(@PathVariable Long nno, Model model){
        BoardResDto resDto = boardService.findById(nno);
        List<CommentResDto> commentList = resDto.getCommentList();

        if (commentList != null && !commentList.isEmpty())
        {
            model.addAttribute("commentList", commentList); // 댓글
        }
        boardService.updateView(nno); //조회수
        model.addAttribute("board", resDto);

        return "/board/boardDtl";
    }

    @GetMapping(value = "/board/post")
    public String boardPost(){
        return "/board/post";
    }
}
