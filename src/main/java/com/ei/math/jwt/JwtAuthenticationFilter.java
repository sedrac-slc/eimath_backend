package com.ei.math.jwt;

import com.ei.math.entity.UserPeople;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@NoArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserPeople userPeople = new ObjectMapper().readValue(request.getReader(), UserPeople.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    userPeople.getUsername(), userPeople.getPassword(), userPeople.getRoles()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token = TokenUtils.createToken(user.getUsername(),"");
        
        response.addHeader("Authorization", "Bearer " + token);
        //response.addHeader("Access-Control-Allow-Origin", "*");
        
        response.getWriter().write("{\"Authorization\":\"Bearer "+token+"\"}");
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
