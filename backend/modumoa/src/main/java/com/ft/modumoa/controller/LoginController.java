package com.ft.modumoa.controller;

import com.ft.modumoa.config.auth.PrincipalDetails;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/oauth2/authorization")
    public String login() {
        return "redirect:/oauth2/authorization/intra42";
    }


    // test
    @ResponseBody
    @GetMapping("/user")
    public String user() {
        return "user";
    }

}