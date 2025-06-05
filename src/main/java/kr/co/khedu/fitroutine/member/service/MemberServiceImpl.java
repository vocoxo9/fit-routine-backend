package kr.co.khedu.fitroutine.member.service;

import kr.co.khedu.fitroutine.member.model.dao.MemberMapper;
import kr.co.khedu.fitroutine.member.model.dto.MemberProfile;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;
    public MemberServiceImpl (MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberProfile getMemberProfile(int memberId) {
        MemberProfile memberProfile = memberMapper.getMemberProfile(memberId);
        return memberProfile;
    }
}
