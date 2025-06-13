package kr.co.khedu.fitroutine.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyRank {
    private int rank;
    private long count;

    @Builder
    public MyRank(int rank, long count) {
        this.rank = rank;
        this.count = count;
    }
}
