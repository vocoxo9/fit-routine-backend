package kr.co.khedu.fitroutine.member.model.dao;

import kr.co.khedu.fitroutine.member.model.dto.MemberProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberProfile getMemberProfile(int memberId);
}
