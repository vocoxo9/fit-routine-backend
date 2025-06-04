package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class MemberDTO {
    private String email;
    private String nickname;
    private String birth;
    private String phone;
    private char gender;
    private int height;
    private int weight;

    @Builder
    public MemberDTO(String email, String nickname, String birth, String phone, char gender, int height, int weight) {
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.phone = phone;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }
}
