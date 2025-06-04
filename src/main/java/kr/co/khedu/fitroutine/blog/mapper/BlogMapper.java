package kr.co.khedu.fitroutine.blog.mapper;

import kr.co.khedu.fitroutine.blog.model.dto.BlogDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    BlogDetail getBlogDetail(long memberId, long viewerId);
}
