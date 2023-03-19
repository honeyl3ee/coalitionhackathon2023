package com.ft.modumoa.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MyPageDTO {

    private String intraId;
    private List<MyPartyDTO> created;
    private List<MyPartyDTO> participated;
}
