package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResignReason {
    private List<String> selectedReason;
    private String inputReason;
}
