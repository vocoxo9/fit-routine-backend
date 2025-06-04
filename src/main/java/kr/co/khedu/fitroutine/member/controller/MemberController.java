package kr.co.khedu.fitroutine.member.controller;

import kr.co.khedu.fitroutine.member.model.dto.MemberDTO;
import kr.co.khedu.fitroutine.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;
    public MemberController (MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/profile")
    public MemberDTO getMemberProfile(@RequestParam("memberId") int memberId) {
        System.out.println("요청 들어옴a" + memberId);
        MemberDTO memberProfile = memberService.getMemberProfile(memberId);
        System.out.println("반환하는 멤버 프로필 :: " + memberProfile);
        return memberProfile;
    }
}
