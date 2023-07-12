package spring.web.project1.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDto {

    @NotBlank(message = "이메일은 필수 입력값 입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력값 입니다.")
    private String nickName;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    @Length(min = 4, max = 16, message = "최소 4자리 최대 16자리까지 가능합니다.")
    private String password;
}
