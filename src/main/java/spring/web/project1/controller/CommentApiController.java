package spring.web.project1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.web.project1.dto.CommentReqDto;
import spring.web.project1.dto.CommentUpdateDto;
import spring.web.project1.entity.Comment;
import spring.web.project1.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping(value = "/api/post/comment/getall")
    public List<Comment> getall(){
        return commentService.getall();
    }

    @PostMapping(value = "/api/post/{nno}/comment")
    public Long save(@PathVariable Long nno, @RequestBody CommentReqDto commentReqDto){
        return commentService.commentSave(nno, commentReqDto);
    }

    @PutMapping(value = "api/post/{nno}/comment/{id}")
    public Long update(@PathVariable Long nno, @PathVariable Long id, @RequestBody CommentUpdateDto commentUpdateDto){
        return commentService.Update(nno, id, commentUpdateDto);
    }

    @DeleteMapping(value = "api/post/{nno}/comment/{id}")
    public Long delete(@PathVariable Long nno, @PathVariable Long id){
        commentService.delete(nno, id);
        return id;
    }
}
