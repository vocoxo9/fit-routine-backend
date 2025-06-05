package kr.co.khedu.fitroutine.member.controller;

import kr.co.khedu.fitroutine.member.model.dto.MemberProfile;
import kr.co.khedu.fitroutine.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;
    public MemberController (MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getMemberProfile(@RequestParam("memberId") int memberId) {
        System.out.println("요청 들어옴a" + memberId);
        MemberProfile memberProfile = memberService.getMemberProfile(memberId);
        System.out.println("반환하는 멤버 프로필 :: " + memberProfile);
        return ResponseEntity.ok(memberProfile);
    }
}
