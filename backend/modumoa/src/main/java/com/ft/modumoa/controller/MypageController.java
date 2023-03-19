package com.ft.modumoa.controller;

import com.ft.modumoa.config.auth.PrincipalDetails;
import com.ft.modumoa.dto.MyPageDTO;
import com.ft.modumoa.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MypageController {

    @Autowired
    private MyPageService myPageService;

    @GetMapping("/mypage")
    public MyPageDTO getMyInfo(@AuthenticationPrincipal PrincipalDetails user) {

        return myPageService.getMyInfo(user.getUser());
    }
}
