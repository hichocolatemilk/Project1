package spring.web.project1.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
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
    private MultipartFile file;

    public Board toEntity(){
        return Board.builder()
                .nno(nno)
                .title(title)
                .content(content)
                .writer(writer)
                .file(String.valueOf(file))
                .build();
    }
}
