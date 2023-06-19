package spring.web.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.web.project1.dto.BoardResDto;
import spring.web.project1.dto.BoardSaveDto;
import spring.web.project1.dto.BoardUpdateDto;
import spring.web.project1.entity.Board;
import spring.web.project1.repository.BoardRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

//    @Value("${file.upload-dir}")
//    private String filesPath;

    public Page<Board> getList (Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    public Page<Board> search(String searchTitle, Pageable pageable){
        return boardRepository.findByTitle(searchTitle, pageable);
    }


    //rest
    public List<Board> getAll(){
        return boardRepository.findAll();
    }

//    public Long save(BoardSaveDto boardSaveDto, MultipartFile file
//    ) throws IOException {
//
//        UUID uuid = UUID.randomUUID();
//        String fileName = uuid + "_" + file.getOriginalFilename();
//        File saveFile = new File(filesPath, "name");
//        file.transferTo(saveFile);
//        boardSaveDto.setFileName(fileName);
//        boardSaveDto.setFilePath(filesPath + fileName);
//        return boardRepository.save(boardSaveDto.toEntity()).getNno();
//    }

    public Long save(BoardSaveDto boardSaveDto) throws IOException {

        return boardRepository.save(boardSaveDto.toEntity()).getNno();
    }

    public Long update(Long nno, BoardUpdateDto boardUpdateDto){
        Board board = boardRepository.findById(nno)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        board.update(boardUpdateDto.getTitle(), boardUpdateDto.getContent());

        return nno;
    }

    public void delete(Long nno){
        Board board = boardRepository.findById(nno).orElseThrow(
                () -> new IllegalStateException("해당 게시글 없음")
        );
        boardRepository.delete(board);
    }

    public BoardResDto findById(Long nno) {
        Board board = boardRepository.findById(nno).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글 없음" + nno));
        return new BoardResDto(board);
    }

    public int updateView(Long nno){
        return boardRepository.updateView(nno);
    }
}
