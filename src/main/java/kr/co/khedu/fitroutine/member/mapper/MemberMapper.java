package kr.co.khedu.fitroutine.member.mapper;

import jakarta.annotation.Nullable;
import kr.co.khedu.fitroutine.member.model.dto.MemberEditInfo;
import kr.co.khedu.fitroutine.member.model.dto.MemberProfile;
import kr.co.khedu.fitroutine.member.model.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    @Nullable Member findMemberByEmail(String email);

    Member findMemberByBlogId(long blogId);

    MemberProfile getMemberProfile(long memberId);

    int editMemberProfile(MemberEditInfo memberEditInfo);

    int updateMemberDetail(MemberEditInfo memberEditInfo);
}
