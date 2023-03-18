package com.ft.modumoa.controller;

import com.ft.modumoa.dto.PartyListDTO;
import com.ft.modumoa.service.PartyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping("/party")
    public List<PartyListDTO> getPartyList(){
        return partyService.getAllPartyList();
    }
}
