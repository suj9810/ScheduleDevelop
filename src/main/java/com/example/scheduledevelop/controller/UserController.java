package com.example.scheduledevelop.controller;


import com.example.scheduledevelop.dto.LoginRequestDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request, HttpServletRequest httpServletRequest) {

        boolean isValidUser = memberService.validateUser(request.getEmail(), request.getPassword(), httpServletRequest);

        if (isValidUser) {
            return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
        }

    }
}
