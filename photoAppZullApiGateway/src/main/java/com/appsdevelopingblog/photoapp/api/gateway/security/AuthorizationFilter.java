package com.appsdevelopingblog.photoapp.api.gateway.security;

import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    Environment environment;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
        super(authenticationManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));

        if(authorizationHeader == null || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))){
            chain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationFilter = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationFilter);
        chain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(environment.getProperty("authorization.token.header.name"));
//        String token = request.getHeader(SecurityConstants.H);
//        if(authorizationHeader == null){
//            return null;
//        }
//        String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"),"");
        System.out.println(token);
        if (token != null) {
            // parse the token.
            String user =
                    Jwts.parser()
                            .setSigningKey(environment.getProperty("token.secret"))
                            .parseClaimsJws(token.replace(environment.getProperty("authorization.token.header.prefix"),""))
                            .getBody()
                            .getSubject();
            System.out.println(user);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;

//        String token = authorizationHeader.replace(environment.getProperty("authorization.token.header.prefix"),"");
//        System.out.println(token);
//        String userId = Jwts.parser()
//                .setSigningKey(environment.getProperty("token.secret"))
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//        System.out.println(userId);
//        if (userId == null){
//            return null;
//        }
//        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }
}
