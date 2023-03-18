package com.ft.modumoa.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PartyEditRequestDTO {

    private String title;
    private String content;
    private String category;
    private int max;
    private LocalDateTime dueDate;
}
