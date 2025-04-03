package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.dto.SignUpResponseDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, String email) {
        Member member = new Member(username, email, password);
        Member saveMember = memberRepository.save(member);
        return new SignUpResponseDto(saveMember.getId(), saveMember.getUsername(), saveMember.getEmail());
    }


    public MemberResponseDto findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id : " + id);
        }
        Member findMember = optionalMember.get();
        return new MemberResponseDto(findMember.getUsername(), findMember.getEmail());
    }
}
