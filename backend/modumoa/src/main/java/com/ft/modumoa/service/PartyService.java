package com.ft.modumoa.service;

import com.ft.modumoa.dto.*;
import com.ft.modumoa.entity.Member;
import com.ft.modumoa.entity.Party;
import com.ft.modumoa.entity.User;
import com.ft.modumoa.repository.CategoryRepository;
import com.ft.modumoa.repository.MemberRepository;
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
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    public List<PartyListDTO> getAllPartyList() {

        return partyRepository.findAll()
                .stream()
                .map(this::convertEntityToPartyListDTO)
                .collect(Collectors.toList());
    }

    public PartyResponseDTO createParty(PartyCreateRequestDTO partyRequestDTO, User user) {

        Party party = convertDtoToEntity(partyRequestDTO, user);

        party = partyRepository.save(party);

        return makePartyResponseDTO(party.getId());
    }

    public PartyInfoDTO getPartyInfo(Long id) {

        Party party = partyRepository.getReferenceById(id);

        return convertEntityToPartyInfoDTO(party);
    }

    public PartyResponseDTO editParty(Long id, PartyEditRequestDTO partyEditRequestDTO) {

        Party party = partyRepository.getReferenceById(id);

        party.setTitle(partyEditRequestDTO.getTitle());
        party.setContent(partyEditRequestDTO.getContent());
        party.setCategory(categoryRepository.getByType(partyEditRequestDTO.getCategory()));
        party.setMax(partyEditRequestDTO.getMax());
        party.setDeadline(partyEditRequestDTO.getDueDate());

        partyRepository.save(party);

        return makePartyResponseDTO(id);
    }

    public PartyResponseDTO deleteParty(Long id) {
        partyRepository.deleteById(id);

        return makePartyResponseDTO(id);
    }

    public void participateParty(Long partyId, User user) {

        Party party = partyRepository.getReferenceById(partyId);
        int current = party.getCurrent();
        party.setCurrent(++current);
        partyRepository.save(party);

        Member member = Member.builder()
                .user(user)
                .party(party)
                .build();
        memberRepository.save(member);
    }

    public void cancelParty(Long partyId, User user) {
        Member member = memberRepository.getMember(user.getId(), partyId);
        memberRepository.delete(member);

        Party party = partyRepository.getReferenceById(partyId);
        int current = party.getCurrent();
        party.setCurrent(--current);
        partyRepository.save(party);
    }

    public PartyResponseDTO makePartyResponseDTO(Long partyId) {

        return new PartyResponseDTO(partyId);
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

    private Party convertDtoToEntity(PartyCreateRequestDTO partyRequestDTO, User user) {

        return Party.builder()
                .writer(user)
                .category(categoryRepository.getByType(partyRequestDTO.getCategory()))
                .title(partyRequestDTO.getTitle())
                .content(partyRequestDTO.getContent())
                .max(partyRequestDTO.getMax())
                .current(1)
                .create_at(LocalDateTime.now())
                .deadline(partyRequestDTO.getDueDate())
                .build();
    }
}
