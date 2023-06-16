package spring.web.project1.dto;

import lombok.*;
import spring.web.project1.entity.Board;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardSaveDto {

    private Long nno;
    private String title;
    private String content;
    private String writer;
    private String fileName;
    private String filePath;

    public Board toEntity(){
        return Board.builder()
                .nno(nno)
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
