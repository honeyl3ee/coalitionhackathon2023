package com.ft.modumoa.config.jwt;

import com.ft.modumoa.config.auth.PrincipalDetails;
import com.ft.modumoa.config.auth.PrincipalDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@AllArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final PrincipalDetailsService principalDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String bearer = req.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(bearer);

        if (bearer == null || !bearer.startsWith(JwtProperties.TOKEN_PREFIX)) {

            chain.doFilter(request, response);
        } else {

            String jwtToken = bearer.substring(JwtProperties.TOKEN_PREFIX.length());
            VerifyResult result = jwtUtil.verifyToken(jwtToken);

            if (result.isSuccess()) {

                PrincipalDetails user = (PrincipalDetails) principalDetailsService.loadUserByUsername(result.getUsername());

                System.out.println("인증 성공. user : " + result.getUsername());
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(userToken);

                chain.doFilter(request, response);

            } else {
                throw new AuthenticationException("TOKEN is not valid");
            }
        }
    }
}
