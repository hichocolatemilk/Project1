package spring.web.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.web.project1.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
