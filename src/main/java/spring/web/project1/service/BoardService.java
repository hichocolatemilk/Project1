package spring.web.project1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.web.project1.dto.BoardResDto;
import spring.web.project1.dto.BoardSaveDto;
import spring.web.project1.dto.BoardUpdateDto;
import spring.web.project1.entity.Board;
import spring.web.project1.repository.BoardRepository;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    @Value("${file.upload-dir}")
    private String filesPath;

    public Page<Board> getList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    public Page<Board> search(String searchTitle, Pageable pageable) {
        return boardRepository.findByTitle(searchTitle, pageable);
    }


    //rest
    public List<Board> getAll() {

        return boardRepository.findAll();
    }

    public Long save(BoardSaveDto boardSaveDto) throws IOException {
        Board board = boardSaveDto.toEntity();

//        // 파일 처리
//        List<String> fileUrls = new ArrayList<>();
//        for (MultipartFile files : file) {
//            // 파일을 저장하고 파일 URL을 얻어온다고 가정합니다.
//            String fileUrl = saveFiles(file).toString();
//            fileUrls.add(fileUrl);
//        }
//
//        // 파일 URL 리스트를 Board 엔티티에 저장합니다.
//        board.setFileUrls(fileUrls.toString());

        // Board 엔티티를 저장하고 생성된 게시물의 Nno를 반환합니다.
        return boardRepository.save(board).getNno();
    }

//    private List<String> saveFiles(List<MultipartFile> files) throws IOException {
//        List<String> fileUrls = new ArrayList<>();
//
//        for (MultipartFile file : files) {
//            // 파일을 저장할 디렉토리 경로 설정
//            String uploadDir = filesPath; // 실제 저장할 디렉토리 경로로 수정해야 합니다.
//
//            // 파일 이름 생성
//            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
//            String extension = getFileExtension(originalFilename);
//            String newFilename = UUID.randomUUID().toString() + "." + extension;
//
//            // 저장할 경로 설정
//            Path targetLocation = Path.of(uploadDir, newFilename);
//
//            // 파일 저장
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            // 파일 URL 추가
//            String fileUrl = "/files/" + newFilename; // 실제 파일 URL로 수정해야 합니다.
//            fileUrls.add(fileUrl);
//        }
//
//        return fileUrls;
//    }


    public Long update(Long nno, BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findById(nno)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        board.update(boardUpdateDto.getTitle(), boardUpdateDto.getContent());

        return nno;
    }

    public void delete(Long nno) {
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

    public int updateView(Long nno) {
        return boardRepository.updateView(nno);
    }


//    private String getFileExtension(String filename) {
//        // 파일 확장자 추출 로직 구현 (예: ".jpg", ".txt")
//        // 필요에 따라서 수정해야 합니다.
//        // 예시로 파일 이름에서 마지막 "."을 찾아 확장자를 추출하는 코드를 사용했습니다.
//        int dotIndex = filename.lastIndexOf(".");
//        return (dotIndex == -1) ? "" : filename.substring(dotIndex);
//    }
}
