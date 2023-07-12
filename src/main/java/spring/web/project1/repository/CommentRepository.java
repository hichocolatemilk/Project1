package spring.web.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.web.project1.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {

}
