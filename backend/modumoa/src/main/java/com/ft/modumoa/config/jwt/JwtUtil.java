package com.ft.modumoa.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String tokenSecret;

    public String makeAuthToken(Authentication authentication) {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        LocalDateTime localDateTime = LocalDateTime.now();
        int sec = JwtProperties.EXPIRATION_TIME / 1000;    // 1일
        localDateTime = localDateTime.plusSeconds(sec);

        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date expireDate = Date.from(localDateTime.atZone(defaultZoneId).toInstant());

        String token = JWT.create()
                .withSubject(oAuth2User.getAttributes().get("login").toString())
                .withClaim("id", Long.parseLong(oAuth2User.getAttributes().get("id").toString()))
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(tokenSecret));

        return token;
    }

// todo jwt 토큰에 유니크 아이디 넣기, 해석할 때 id 뽑아서 검증하기
    public VerifyResult verifyToken(String token) {
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(tokenSecret)).build().verify(token);

            return VerifyResult.builder()
                    .success(true)
                    .username(verify.getSubject()).build();
        } catch (Exception e) {

            DecodedJWT decode = JWT.decode(token);

            return VerifyResult.builder()
                    .success(false)
                    .username(decode.getSubject()).build();
        }
    }

}
