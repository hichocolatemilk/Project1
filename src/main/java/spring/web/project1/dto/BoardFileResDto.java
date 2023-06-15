package spring.web.project1.dto;

import lombok.*;
import spring.web.project1.entity.BoardFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFileResDto {

    private String fileName;
    private String fileType;
    private long fileSize;
}
