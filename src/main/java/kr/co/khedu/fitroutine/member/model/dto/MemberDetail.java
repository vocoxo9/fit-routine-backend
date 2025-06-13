package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class MemberDetail {

    private final double height;
    private final double weight;

    public MemberDetail(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

}
