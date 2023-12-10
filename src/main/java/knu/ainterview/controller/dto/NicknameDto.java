package knu.ainterview.controller.dto;

import knu.ainterview.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NicknameDto {
    String nickname;

    public static NicknameDto of(Member member) {
        return new NicknameDto(member.getNickname());}
}
