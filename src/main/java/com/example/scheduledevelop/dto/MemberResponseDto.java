package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final String username;

    private final String email;

    private final String password;

    public MemberResponseDto(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
