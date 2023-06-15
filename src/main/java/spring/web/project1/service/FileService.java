package spring.web.project1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import spring.web.project1.entity.BoardFile;
import spring.web.project1.repository.FileRepoistory;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileService {

    private final FileRepoistory fileRepository;

    @Value("${file.upload-dir}")
    private String projectPath;

    public void deleteFile(String filePath){
        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일삭제");
        }else {
            log.info("파일 존재하지 않음");
        }
    }

//    public Long saveFile(BoardFileResDto boardFileResDto, MultipartFile multipartFile) throws Exception {
//        UUID uuid = UUID.randomUUID();
//        String oriFileName = uuid + "_" + boardFileResDto.getOriFileName();
//
//        File saveFile = new File(projectPath, oriFileName);
//        multipartFile.transferTo(saveFile);
//
//        return fileRepository.save(boardFileResDto.toEntity()).getId();
//    }

    public BoardFile saveFile(MultipartFile file)throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception(("Filename error"));
            }
            BoardFile boardFile = new BoardFile(fileName,
                    file.getContentType(),
                    file.getBytes());
            return fileRepository.save(boardFile);
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }




}
