package com.appsdeveloperblog.photoapp.api.gateway.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class  AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter> {

    @Autowired
    Environment environment;

    public AuthorizationHeaderFilter() {
        super(AuthorizationHeaderFilter.class);
    }

    public static class Config{

    }
    @Override
    public GatewayFilter apply(AuthorizationHeaderFilter config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return onError(exchange, "noAuthorizationHeader", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer","");
            if(!isJwtValid(jwt)){
                return onError(exchange, "JwtIsNoValid", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);


        };
    }

    private Mono<Void> onError(ServerWebExchange serverWebExchange, String message, HttpStatus httpStatus){
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
    private boolean isJwtValid(String jwt){
        boolean returnValue = true;

        String subject = null;
        try {


            subject = Jwts.parser()
                    .setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        }
        catch (Exception ex){
            returnValue=false;
        }
        if(subject==null || subject.isEmpty()){
            returnValue=false;
        }

        return returnValue;
    }
}
