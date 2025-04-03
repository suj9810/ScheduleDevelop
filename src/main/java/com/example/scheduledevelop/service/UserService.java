package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.repository.UserRository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRository userRository;

    public MemberResponseDto validateUser(String email, String password) {

        // step1. 해당 email 로 find 해서 Member가 있나 없으면 Exception
        Member member = userRository.findMemberByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다."));

        // step2. password 비교
        if (!member.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return new MemberResponseDto(member);

    }

}
