package com.ft.modumoa.repository;

import com.ft.modumoa.entity.Member;
import com.ft.modumoa.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByParty(Party party);
}
