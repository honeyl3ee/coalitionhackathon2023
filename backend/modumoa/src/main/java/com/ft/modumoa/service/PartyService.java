package com.ft.modumoa.service;

import com.ft.modumoa.dto.PartyListDTO;
import com.ft.modumoa.entity.Party;
import com.ft.modumoa.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    public List<PartyListDTO> getAllPartyList(){

        return partyRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private PartyListDTO convertEntityToDto(Party party){

        return PartyListDTO.builder()
                .id(party.getId())
                .writer(party.getWriter().getIntraId())
                .title(party.getTitle())
                .category(party.getCategory().getType())
                .max(party.getMax())
                .current(party.getCurrent())
                .createAt(party.getCreate_at())
                .due_date(party.getDeadline())
                .status(LocalDateTime.now().isBefore(party.getDeadline()))
                .build();
    }
}
