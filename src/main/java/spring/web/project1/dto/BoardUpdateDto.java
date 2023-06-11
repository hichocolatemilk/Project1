package spring.web.project1.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUpdateDto {

    private String title;
    private String content;

}
