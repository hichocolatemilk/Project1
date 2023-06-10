package spring.web.project1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.web.project1.constant.Role;
import spring.web.project1.dto.MemberDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 25)
    private String nickName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    public static Member register(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        String password = passwordEncoder.encode(memberDto.getPassword());
        return member.builder()
                .email(memberDto.getEmail())
                .nickName(memberDto.getNickName())
                .password(password)
                .role(Role.ADMIN)
                .build();
    }
}
