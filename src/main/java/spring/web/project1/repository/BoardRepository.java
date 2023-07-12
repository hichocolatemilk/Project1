package spring.web.project1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.web.project1.entity.Board;



public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.view = b.view +1 where b.nno = :nno")
    int updateView(@Param("nno") Long nno);

    Page<Board> findByTitle(String searchTitle, Pageable pageable);


}
