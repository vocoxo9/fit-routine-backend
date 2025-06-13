package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
public final class MemberDetail {

    private final double height;
    private final double weight;
    private final Date birthAt;
    private final String gender;

    @Builder
    public MemberDetail(double height, double weight, Date birthAt, String gender) {
        this.height = height;
        this.weight = weight;
        this.birthAt = birthAt;
        this.gender = gender;
    }

}
