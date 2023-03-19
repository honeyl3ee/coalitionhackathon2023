package com.ft.modumoa.service;

import com.ft.modumoa.dto.MyPageDTO;
import com.ft.modumoa.dto.MyPartyDTO;
import com.ft.modumoa.entity.Member;
import com.ft.modumoa.entity.Party;
import com.ft.modumoa.entity.User;
import com.ft.modumoa.repository.MemberRepository;
import com.ft.modumoa.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyPageService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private MemberRepository memberRepository;

    public MyPageDTO getMyInfo(User user) {

        List<MyPartyDTO> created = partyRepository.findByWriter(user)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());

        List<Member> member = memberRepository.findByUser(user);
        List<Party> party = new ArrayList<>();
        for (Member m : member) {
            party.add(m.getParty());
        }
        List<MyPartyDTO> participated = party
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());

        return MyPageDTO.builder()
                .intraId(user.getIntraId())
                .created(created)
                .participated(participated)
                .build();
    }

    private MyPartyDTO convertEntityToDTO(Party party) {

        return MyPartyDTO.builder()
                .id(party.getId())
                .writer(party.getWriter().getIntraId())
                .title(party.getTitle())
                .category(party.getCategory().getType())
                .max(party.getMax())
                .current(party.getCurrent())
                .createAt(party.getCreate_at())
                .dueDate(party.getDeadline())
                .status(LocalDateTime.now().isBefore(party.getDeadline()))
                .build();
    }


}
