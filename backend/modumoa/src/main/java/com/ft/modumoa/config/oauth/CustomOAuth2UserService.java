package com.ft.modumoa.config.oauth;

import com.ft.modumoa.entity.User;
import com.ft.modumoa.enums.Role;
import com.ft.modumoa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        Map<String, Object> userInfo = oAuth2User.getAttributes();

        checkNewUser(userInfo);

        return oAuth2User;
    }

    private void checkNewUser(Map<String, Object> userInfo) {

        String intraId = userInfo.get("login").toString();

        if (userRepository.findByIntraId(intraId) == null) {
            User user = User.builder()
                    .uniqueId(Long.valueOf(userInfo.get("id").toString()))
                    .intraId(userInfo.get("login").toString())
                    .role(Role.USER)
                    .build();

            userRepository.save(user);
        }
    }

}
