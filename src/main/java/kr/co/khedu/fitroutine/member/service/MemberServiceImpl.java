package kr.co.khedu.fitroutine.member.service;

import kr.co.khedu.fitroutine.member.model.dao.MemberMapper;
import kr.co.khedu.fitroutine.member.model.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;
    public MemberServiceImpl (MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberDTO getMemberProfile(int memberId) {
        System.out.println("111");
        MemberDTO memberProfile = memberMapper.getMemberProfile(memberId);
        System.out.println("222");
        return memberProfile;
    }
}
