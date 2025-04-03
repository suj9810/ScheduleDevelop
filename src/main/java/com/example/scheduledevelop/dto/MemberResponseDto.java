package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.email = member.getEmail();
    }
}
