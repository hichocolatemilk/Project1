package spring.web.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.web.project1.dto.FileUploadDto;
import spring.web.project1.repository.FileRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {

    private final FileRepository fileRepository;

    public Long saveFile(FileUploadDto fileUploadDto){
        return fileRepository.save(fileUploadDto.toEntity()).getId();
    }


}
