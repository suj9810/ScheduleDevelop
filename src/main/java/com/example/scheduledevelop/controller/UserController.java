package com.example.scheduledevelop.controller;


import com.example.scheduledevelop.common.Const;
import com.example.scheduledevelop.dto.LoginRequestDto;
import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<MemberResponseDto> login(@RequestBody LoginRequestDto request, HttpServletRequest httpServletRequest) {

        MemberResponseDto validUser = userService.validateUser(request.getEmail(), request.getPassword());
        HttpSession session = httpServletRequest.getSession(); // session을 가져온다.
        session.setAttribute(Const.LOGIN_USER, validUser); // session 에 데이터를 저장한다.

        return new ResponseEntity<>(validUser, HttpStatus.OK);

    }
}
