package com.example.scheduledevelop.controller;

import com.example.scheduledevelop.common.Const;
import com.example.scheduledevelop.dto.CreateScheduleRequestDto;
import com.example.scheduledevelop.dto.MemberResponseDto;
import com.example.scheduledevelop.dto.ScheduleResponseDto;
import com.example.scheduledevelop.dto.UpdateScheduleRequestDto;
import com.example.scheduledevelop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();

        MemberResponseDto memberResponseDto = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(requestDto.getTitle(), requestDto.getContents(), memberResponseDto.getId());
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto findByIdScheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(findByIdScheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequestDto requestDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        MemberResponseDto memberResponseDto = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);
        scheduleService.update(id, requestDto.getTitle(), requestDto.getContents(), memberResponseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        MemberResponseDto memberResponseDto = (MemberResponseDto) session.getAttribute(Const.LOGIN_USER);

        scheduleService.delete(id, memberResponseDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
