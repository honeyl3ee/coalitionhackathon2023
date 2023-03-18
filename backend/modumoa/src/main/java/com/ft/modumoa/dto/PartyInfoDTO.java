package com.ft.modumoa.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PartyInfoDTO {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private String category;
    private int max;
    private int current;
    private LocalDateTime dueDate;
    private List<String> participator;
    private boolean status;

}
