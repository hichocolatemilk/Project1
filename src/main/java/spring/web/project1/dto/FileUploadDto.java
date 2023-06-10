package spring.web.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.web.project1.entity.File;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileUploadDto {

    private Long id;
    private String fileName;
    private String oriFileName;
    private String filePath;

    public File toEntity(){
        return File.builder()
                .id(id)
                .fileName(fileName)
                .oriFileName(oriFileName)
                .filePath(filePath)
                .build();
    }
}
