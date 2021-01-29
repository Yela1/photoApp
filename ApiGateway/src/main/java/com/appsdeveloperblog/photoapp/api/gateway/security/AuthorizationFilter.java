//package com.appsdeveloperblog.photoapp.api.gateway.security;
//
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//
//import java.io.IOException;
//
//public class AuthorizationFilter extends BasicAuthenticationFilter {
//    private Environment environment;
//
//    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
//        super(authenticationManager);
//        this.environment = environment;
//    }
//
//    @Override
//    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain chain) throws IOException, ServletException {
//        super.doFilterInternal(request, response, chain);
//    }
//}
