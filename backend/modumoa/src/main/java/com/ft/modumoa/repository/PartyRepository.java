package com.ft.modumoa.repository;

import com.ft.modumoa.entity.Party;
import com.ft.modumoa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {

    List<Party> findByWriter(User user);
}