package kr.co.khedu.fitroutine.post.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;


@Data
public final class SimplePost {
    private long postId;
    private String title;
    private Date createdAt;
}

//@Getter
//public final class SimplePost {
//    private final long postId;
//    private final String title;
//    private final Date createdAt;
//
//    @Builder
//    private SimplePost(long postId, String title, Date createdAt) {
//        this.postId = postId;
//        this.title = title;
//        this.createdAt = createdAt;
//    }
//}
