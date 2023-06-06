package spring.web.project1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.web.project1.constant.Role;
import spring.web.project1.dto.MemberDto;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member register(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        String password = passwordEncoder.encode(memberDto.getPassword());
        return member.builder()
                .email(memberDto.getEmail())
                .nickName(memberDto.getNickName())
                .password(password)
                .build();
    }
}
