package com.ft.modumoa.service;

import com.ft.modumoa.entity.Member;
import com.ft.modumoa.entity.Party;
import com.ft.modumoa.repository.MemberRepository;
import com.ft.modumoa.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PartyRepository partyRepository;

    public List<String> getMemberList(Party party) {
        return memberRepository.findByParty(party)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private String convertEntityToDTO(Member member) {
        return member.getUser().getIntraId();
    }
}
