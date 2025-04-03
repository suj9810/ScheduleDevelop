package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.dto.*;
import com.example.scheduledevelop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto signUpResponseDto = memberService.signUp(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updated(
            @PathVariable Long id,
            @RequestBody UpdateMemberRequestDto requestDto
    ) {
        memberService.update(id, requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestBody UpdateMemberRequestDto requestDto
    ) {
        memberService.delete(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
