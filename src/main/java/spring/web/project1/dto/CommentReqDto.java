package spring.web.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.web.project1.entity.Board;
import spring.web.project1.entity.Comment;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentReqDto {

    private Long id;
    private String comment;
    private String commentWriter;
    private Board board;

    public Comment toEntity(){
        return Comment.builder()
                .id(id)
                .comment(comment)
                .commentWriter(commentWriter)
                .board(board)
                .build();
    }

}
