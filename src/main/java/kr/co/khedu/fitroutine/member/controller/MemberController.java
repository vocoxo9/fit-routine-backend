package kr.co.khedu.fitroutine.member.controller;

import kr.co.khedu.fitroutine.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public final class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<?> getMemberProfile() {
        // 추후에 토큰에서 회원을 얻어도록 변경해야 합니다.
        long memberId = 1;

        return ResponseEntity.ok(memberService.getMemberProfile(memberId));
    }
}
