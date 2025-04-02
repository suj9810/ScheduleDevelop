package com.example.scheduledevelop.dto;

import com.example.scheduledevelop.entity.BaseEntity;
import lombok.Getter;

@Getter
public class ScheduleResponseDto extends BaseEntity {

    private final Long id;

    private final String title;

    private final String contents;

    public ScheduleResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }
}
