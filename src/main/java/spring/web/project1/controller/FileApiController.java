package spring.web.project1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.web.project1.dto.BoardFileResDto;
import spring.web.project1.entity.BoardFile;
import spring.web.project1.service.FileService;

@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileService fileService;

    @PostMapping("/upload")
    public BoardFileResDto uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        BoardFile boardFile = null;
        boardFile = fileService.saveFile(file);
        return new BoardFileResDto(boardFile.getFileName(),
                file.getContentType(),
                file.getSize());
    }
}
