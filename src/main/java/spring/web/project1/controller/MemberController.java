package spring.web.project1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.web.project1.dto.MemberDto;
import spring.web.project1.entity.Member;
import spring.web.project1.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/member/register")
    public String register(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "/member/register";
    }
    @PostMapping(value = "/member/register")
    public String newRegister(@Valid MemberDto memberDto, BindingResult bindingResult,  Model model){
        if(bindingResult.hasErrors()){
            return "member/register";
        }
        try{
            Member member = Member.register(memberDto, passwordEncoder);
            memberService.saveMember(member);
        }catch(IllegalStateException e){
            model.addAttribute("errormessage", e.getMessage());
            return "member/register";
        }
        return "redirect:/member/login";
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
