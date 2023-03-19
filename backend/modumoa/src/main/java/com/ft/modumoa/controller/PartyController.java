package com.ft.modumoa.controller;

import com.ft.modumoa.config.auth.PrincipalDetails;
import com.ft.modumoa.dto.*;
import com.ft.modumoa.service.PartyService;
import com.ft.modumoa.service.SlackBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PartyController {

    @Autowired
    private PartyService partyService;

    @Autowired
    private SlackBotService slackBotService;


    @GetMapping("/party")
    public List<PartyListDTO> getPartyList() {
        return partyService.getAllPartyList();
    }

    @PostMapping("/party/create")
    public PartyResponseDTO createParty(@RequestBody PartyCreateRequestDTO partyRequestDTO, @AuthenticationPrincipal PrincipalDetails user) {

        return partyService.createParty(partyRequestDTO, user.getUser());
    }

    @GetMapping("/party/{id}")
    public PartyInfoDTO getPartyInfo(@PathVariable Long id) {

        return partyService.getPartyInfo(id);
    }

    @PutMapping("/party/{id}/edit")
    public PartyResponseDTO editParty(@PathVariable Long id, @RequestBody PartyEditRequestDTO partyEditRequestDTO) {

        return partyService.editParty(id, partyEditRequestDTO);
    }

    @DeleteMapping("/party/{id}/delete")
    public PartyResponseDTO deleteParty(@PathVariable Long id) {

        return partyService.deleteParty(id);
    }

    @GetMapping("/party/{id}/participate")
    public PartyResponseDTO participateParty(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails user) {

        String message = slackBotService.getPrefixMessage(id, user.getUser());

        partyService.participateParty(id, user.getUser());
        slackBotService.sendMessageToUser(id, message + "에 참여를 신청하였습니다.");
        slackBotService.isFull(id, user.getUser(), message + " 모집이 완료되었습니다.");

        return partyService.makePartyResponseDTO(id);
    }

    @GetMapping("/party/{id}/participate/cancel")
    public PartyResponseDTO cancelParty(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails user) {

        String message = slackBotService.getPrefixMessage(id, user.getUser());

        partyService.cancelParty(id, user.getUser());
        slackBotService.sendMessageToUser(id, message + " 참여를 취소하였습니다.");

        return partyService.makePartyResponseDTO(id);
    }
}
