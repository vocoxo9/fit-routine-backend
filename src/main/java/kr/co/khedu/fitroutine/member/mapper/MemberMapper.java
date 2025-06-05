package kr.co.khedu.fitroutine.member.mapper;

import kr.co.khedu.fitroutine.member.model.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member findMemberById(int memberId);

    Member findMemberByBlogId(long blogId);
}
