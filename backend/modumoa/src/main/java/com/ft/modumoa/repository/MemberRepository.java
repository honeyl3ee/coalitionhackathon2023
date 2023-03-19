package com.ft.modumoa.repository;

import com.ft.modumoa.entity.Member;
import com.ft.modumoa.entity.Party;
import com.ft.modumoa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByParty(Party party);

    List<Member> findByUser(User user);

    @Query(value = "SELECT * FROM member WHERE user_id = :user_id AND party_id = :party_id", nativeQuery = true)
    Member getMember(@Param("user_id") Long userId, @Param("party_id") Long partyId);
}
