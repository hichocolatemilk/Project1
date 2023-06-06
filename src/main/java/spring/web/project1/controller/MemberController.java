package spring.web.project1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.web.project1.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/member/register")
    public String register(){
        return "/member/register";
    }

    @GetMapping(value = "/member/login")
    public String login(){
        return "/member/memberLogin";
    }

    @GetMapping(value = "/member/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 비번 확인");
        return "/member/memberLogin";
    }
}
