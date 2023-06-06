package spring.web.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.web.project1.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
