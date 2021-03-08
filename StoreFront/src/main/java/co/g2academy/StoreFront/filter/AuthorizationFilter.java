package co.g2academy.StoreFront.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static co.g2academy.StoreFront.model.SecurityConstant.HEADER_NAME;
import static co.g2academy.StoreFront.model.SecurityConstant.KEY;

import java.io.IOException;
import java.util.Collections;;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain) throws IOException, 
        ServletException {
            String token = request.getHeader(HEADER_NAME);
            if (token == null) {
                chain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authenticate(token));
            chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(String token) {
        Claims user = Jwts.parser()
            .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
            .parseClaimsJws(token)
            .getBody();
        if(user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }
        return null;
    }
    
}
