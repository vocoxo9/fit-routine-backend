package kr.co.khedu.fitroutine.member.mapper;

import jakarta.annotation.Nullable;
import kr.co.khedu.fitroutine.member.model.dto.*;
import kr.co.khedu.fitroutine.member.model.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Nullable MemberResponse selectMemberById(long memberId);

    @Nullable Member selectMemberByEmail(String email);

    int insertMember(MemberCreateRequest createRequest);

    int insertMemberDetail(MemberCreateRequest createRequest);

    int updateMember(long memberId, MemberUpdateRequest updateRequest);

    int updateMemberDetail(long memberId, MemberUpdateRequest updateRequest);

    MemberPassword selectCurrentPassword(long memberId);

    void insertResignReason(long memberId, String reason);

    int resignMember(long memberId);

    List<MemberNotification> findNotifications(long memberId);
}
