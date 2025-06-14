package kr.co.khedu.fitroutine.todo.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class MyRank {
    private final int rank;
    private final long count;

    @Builder
    private MyRank(int rank, long count) {
        this.rank = rank;
        this.count = count;
    }
}
