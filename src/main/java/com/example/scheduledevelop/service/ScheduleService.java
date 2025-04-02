package com.example.scheduledevelop.service;

import com.example.scheduledevelop.dto.ScheduleResponseDto;
import com.example.scheduledevelop.entity.Member;
import com.example.scheduledevelop.entity.Schedule;
import com.example.scheduledevelop.repository.MemberRepository;
import com.example.scheduledevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto save(String title, String contents, String email) {

        Member findMember = memberRepository.findMemberByEmail(email).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist email : " + email));

        Schedule schedule = new Schedule(title, contents);
        schedule.setMember(findMember);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist id : " + id));
        return new ScheduleResponseDto(findSchedule);
    }

    public ScheduleResponseDto update(Long id, String title, String contents, String password) {
        Schedule findSchedule = scheduleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist id : " + id));
        Member writer = findSchedule.getMember();

        // 이제 정보를 새로 업데이트 하기. 새로운 업데이트 정보를 받았으면 객체를 수정하기.
        // 1. Password 로 작성자 인증
        if (!writer.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 2. 조회한 객체 (findSchedule) 를 새로운 정보(String title, String contents)로 update 객체 데이터 변경하기
        findSchedule.updateSchedule(title, contents);

        // 3. 수정한 객체 repository 로 update 하기
        scheduleRepository.save(findSchedule);

        return new ScheduleResponseDto(findSchedule);
    }

    public void delete(Long id, String password) {
        Schedule findSchedule = scheduleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist id : " + id));

        Member writer = findSchedule.getMember();

        if (!writer.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(findSchedule);
    }
}