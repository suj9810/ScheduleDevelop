package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.dto.SignUpResponseDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.repository.MemberRepository;
import jakarta.transaction.Transactional;
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
        return new MemberResponseDto(findMember);
    }

    @Transactional
    public MemberResponseDto update(Long id, String username, String email, String password) {
        Member findMember = memberRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist id : " + id));

        if (!findMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findMember.updateMember(username, email, password);

        return new MemberResponseDto(findMember);
    }
}
