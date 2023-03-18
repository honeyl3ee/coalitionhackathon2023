package com.ft.modumoa.entity;

import com.ft.modumoa.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private Long uniqueId;

    @Column(unique = true)
    private String intraId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(Long id, Long uniqueId, String intraId, Role role) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.intraId = intraId;
        this.role = role;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }
}
