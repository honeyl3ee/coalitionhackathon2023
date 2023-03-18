package com.ft.modumoa.controller;

import com.ft.modumoa.config.auth.PrincipalDetails;
import com.ft.modumoa.dto.UserInfoDTO;
import com.ft.modumoa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user/me")
    public UserInfoDTO getUserInfo(@AuthenticationPrincipal PrincipalDetails user) {

        return userService.getUserInfo(user);
    }
}
