package spring.web.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.web.project1.dto.CommentReqDto;
import spring.web.project1.dto.CommentResDto;
import spring.web.project1.dto.CommentUpdateDto;
import spring.web.project1.entity.Comment;
import spring.web.project1.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardService boardService;


    public List<Comment> getall(){
        return commentRepository.findAll();
    }

    public Long commentSave(Long nno, CommentReqDto reqDto){
        boardService.findById(nno);
        return   commentRepository.save(reqDto.toEntity()).getId();
    }

    public Long Update(Long nno, Long id, CommentUpdateDto commentUpdateDto){
        boardService.findById(nno);
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 없음"));
        comment.update(commentUpdateDto.getComment());
        return id;

    }
    public void delete(Long nno,Long id){
        boardService.findById(nno);
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없음"));
        commentRepository.delete(comment);
    }

    public CommentResDto findById(Long id) {
        Comment comment =  commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없음"));

        return new CommentResDto(comment);
    }
}
