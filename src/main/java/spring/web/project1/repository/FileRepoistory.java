package spring.web.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.web.project1.entity.BoardFile;

public interface FileRepoistory extends JpaRepository<BoardFile, Long> {
}
