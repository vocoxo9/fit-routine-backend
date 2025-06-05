package kr.co.khedu.fitroutine.member.service;

import kr.co.khedu.fitroutine.member.model.dto.MemberProfile;

public interface MemberService {
    /* 마이페이지-프로필에서 회원정보를 조회 */
    public MemberProfile getMemberProfile(int memberId);
}
