package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.BaseEntity;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto extends BaseEntity {

    private final String title;

    private final String contents;

    private final String email;

    public CreateScheduleRequestDto(String title, String contents, String email) {
        this.title = title;
        this.contents = contents;
        this.email = email;
    }
}
