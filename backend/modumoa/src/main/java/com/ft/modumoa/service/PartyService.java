package com.ft.modumoa.service;

import com.ft.modumoa.dto.PartyCreateResponseDTO;
import com.ft.modumoa.dto.PartyListDTO;
import com.ft.modumoa.dto.PartyCreateRequestDTO;
import com.ft.modumoa.entity.Party;
import com.ft.modumoa.entity.User;
import com.ft.modumoa.repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    public List<PartyListDTO> getAllPartyList() {

        return partyRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public PartyCreateResponseDTO createParty(PartyCreateRequestDTO partyRequestDTO, User user) {

        Party party = convertDtoToEntity(partyRequestDTO, user);

        party = partyRepository.save(party);

        return PartyCreateResponseDTO.builder()
                .id(party.getId())
                .build();
    }

    private PartyListDTO convertEntityToDto(Party party) {

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

    private Party convertDtoToEntity(PartyCreateRequestDTO partyRequestDTO, User user){
        Party party = Party.builder()
                .writer(user)
                .category(categoryRepository.findByType(partyRequestDTO.getCategory()))
                .title(partyRequestDTO.getTitle())
                .content(partyRequestDTO.getContent())
                .max(partyRequestDTO.getMax())
                .current(0)
                .create_at(LocalDateTime.now())
                .deadline(partyRequestDTO.getDueDate())
                .build();
    }
}
