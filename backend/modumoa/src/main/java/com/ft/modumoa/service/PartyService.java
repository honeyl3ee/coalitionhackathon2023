package com.ft.modumoa.service;

import com.ft.modumoa.dto.PartyCreateResponseDTO;
import com.ft.modumoa.dto.PartyInfoDTO;
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
    @Autowired
    private MemberService memberService;

    public List<PartyListDTO> getAllPartyList() {

        return partyRepository.findAll()
                .stream()
                .map(this::convertEntityToPartyListDTO)
                .collect(Collectors.toList());
    }

    public PartyCreateResponseDTO createParty(PartyCreateRequestDTO partyRequestDTO, User user) {

        Party party = convertDtoToEntity(partyRequestDTO, user);

        party = partyRepository.save(party);

        return PartyCreateResponseDTO.builder()
                .id(party.getId())
                .build();
    }

    public PartyInfoDTO getPartyInfo(Long id) {
        Party party = partyRepository.getReferenceById(id);

        return convertEntityToPartyInfoDTO(party);
    }

    private PartyInfoDTO convertEntityToPartyInfoDTO(Party party) {
        return PartyInfoDTO.builder()
                .id(party.getId())
                .writer(party.getWriter().getIntraId())
                .title(party.getTitle())
                .content(party.getContent())
                .category(party.getCategory().getType())
                .max(party.getMax())
                .current(party.getCurrent())
                .dueDate(party.getDeadline())
                .participator(memberService.getMemberList(party))
                .status(LocalDateTime.now().isBefore(party.getDeadline()))
                .build();
    }



    private PartyListDTO convertEntityToPartyListDTO(Party party) {

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

        return Party.builder()
                .writer(user)
                .category(categoryRepository.getByType(partyRequestDTO.getCategory()))
                .title(partyRequestDTO.getTitle())
                .content(partyRequestDTO.getContent())
                .max(partyRequestDTO.getMax())
                .current(0)
                .create_at(LocalDateTime.now())
                .deadline(partyRequestDTO.getDueDate())
                .build();
    }
}
