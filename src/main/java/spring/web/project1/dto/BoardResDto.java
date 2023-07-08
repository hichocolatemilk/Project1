package spring.web.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import spring.web.project1.entity.Board;
import spring.web.project1.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDto {

    private Long nno;
    private String title;
    private String content;
    private String writer;
    private int view;
    private List<CommentResDto> commentList;

    public BoardResDto(Board board){
        this.nno = board.getNno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.view = board.getView();
        this.commentList = board.getCommentList().stream()
                .map(CommentResDto::new).collect(Collectors.toList());
    }
}
