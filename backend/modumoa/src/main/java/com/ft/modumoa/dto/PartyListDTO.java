package com.ft.modumoa.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PartyListDTO {
    private Long id;
    private String writer;
    private String title;
    private String category;
    private int max;
    private int current;
    private LocalDateTime createAt;
    private LocalDateTime due_date;
    private boolean status;
}
