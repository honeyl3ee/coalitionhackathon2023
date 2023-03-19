package com.ft.modumoa.dto;

import lombok.Data;

@Data
public class PartyResponseDTO {

    private Long id;

    public PartyResponseDTO(Long id) {
        this.id = id;
    }
}
