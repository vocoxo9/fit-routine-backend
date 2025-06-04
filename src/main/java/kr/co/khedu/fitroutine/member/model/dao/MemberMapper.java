package kr.co.khedu.fitroutine.member.model.dao;

import kr.co.khedu.fitroutine.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDTO getMemberProfile(int memberId);
}
