package com.ft.modumoa.controller;

import com.ft.modumoa.config.auth.PrincipalDetails;
import com.ft.modumoa.dto.PartyCreateResponseDTO;
import com.ft.modumoa.dto.PartyInfoDTO;
import com.ft.modumoa.dto.PartyListDTO;
import com.ft.modumoa.dto.PartyCreateRequestDTO;
import com.ft.modumoa.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping("/party")
    public List<PartyListDTO> getPartyList() {
        return partyService.getAllPartyList();
    }

    @PostMapping("/party/create")
    public PartyCreateResponseDTO createParty(@RequestBody PartyCreateRequestDTO partyRequestDTO, @AuthenticationPrincipal PrincipalDetails user) {

        return partyService.createParty(partyRequestDTO, user.getUser());
    }

    @GetMapping("/party/{id}")
    public PartyInfoDTO getPartyInfo(@PathVariable Long id) {

        return partyService.getPartyInfo(id);
    }
}
