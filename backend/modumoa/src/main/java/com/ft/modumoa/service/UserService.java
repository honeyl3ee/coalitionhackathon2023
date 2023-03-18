package com.ft.modumoa.service;

import com.ft.modumoa.config.auth.PrincipalDetails;
import com.ft.modumoa.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    public UserInfoDTO getUserInfo(PrincipalDetails user) {

        return UserInfoDTO.builder()
                .intraId(user.getUsername())
                .build();
    }
}
