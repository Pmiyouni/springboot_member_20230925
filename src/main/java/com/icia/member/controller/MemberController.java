package com.icia.member.controller;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
//@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService  memberService;

    @GetMapping("/member/save")
    public String saveform() {
        return "memberPages/memberSave";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("memberDTO = " + memberDTO);
          memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "memberPages/memberLogin";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "memberPages/memberMain";
        } else {
            return "memberPages/memberLogin";
        }
    }

    @GetMapping("/members")
    public String list(Model model){
        List<MemberDTO> memberDTOList=memberService.findAll();
        model.addAttribute("memberlist",memberDTOList);
        return "memberPages/memberList";
    }

   @GetMapping("/member/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
       try {
           MemberDTO memberDTO = memberService.findById(id);
           model.addAttribute("member", memberDTO);
           return "memberPages/memberDetail";
       } catch (NoSuchElementException e) {
           return "NotFound";
       }
   }
    @GetMapping("/member/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        try {
            MemberDTO memberDTO = memberService.findById(id);
            model.addAttribute("member", memberDTO);
            return "memberPages/memberUpdate";
        } catch (NoSuchElementException e) {
            return "NotFound";
        }
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/members";
    }

    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        memberService.deleteById(id);
        return "redirect:/members";
    }




        @GetMapping("/member/logout")
        public String logout(HttpSession session) {
            session.removeAttribute("loginEmail");
//        session.invalidate();
            return "redirect:/";
        }

//        @PostMapping("/member/dup-check")
//        public ResponseEntity duplicateCheck(@RequestParam("memberEmail") String memberEmail) {
//            String checkResult = memberService.emailCheck(memberEmail);
//            return checkResult;
//        if (checkResult != null) {
//            return "ok";
//        } else {
//            return "no";
//        }
        }



