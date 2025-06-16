package kr.co.khedu.fitroutine.member.controller;

import jakarta.validation.Valid;
import kr.co.khedu.fitroutine.member.model.dto.*;
import kr.co.khedu.fitroutine.member.service.MemberService;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@Validated
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> getMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(memberService.getMember(userDetails.getMemberId()));
    }

    @PostMapping("/me/verify-password")
    public ResponseEntity<Boolean> checkCurrentPassword(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody MemberPassword password
    ) {
        return ResponseEntity.ok(memberService.checkCurrentPassword(userDetails.getMemberId(), password));
    }

    @PostMapping
    public ResponseEntity<MemberCreateResponse> createMember(
            @RequestBody @Valid MemberCreateRequest createRequest
    ) {
        return ResponseEntity.ok(memberService.createMember(createRequest));
    }

    @PatchMapping("/me")
    public ResponseEntity<MemberResponse> updateMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid MemberUpdateRequest updateRequest
    ) {
        return ResponseEntity.ok(memberService.updateMember(userDetails.getMemberId(), updateRequest));
    }

    @PostMapping("/me/resign")
    public ResponseEntity<Void> resignMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid ResignReason resignReason
    ) {
        System.out.println("resign member Controller 요청들어옴" + resignReason);
        System.out.println(resignReason.getInputReason());
        memberService.insertResignReason(userDetails.getMemberId(), resignReason);
        System.out.println("요청 끝남");
        return ResponseEntity.ok().build();
    }
}
