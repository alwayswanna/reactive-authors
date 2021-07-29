package a.gleb.reactiverest.configuration;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JWTUtil jwtUtil;

    @Autowired
    public AuthenticationManager(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username;
        try{
            username = jwtUtil.extractTokenInformation(authToken);
        }catch (Exception e){
            username = null;
            log.warn(e.getMessage());
        }
        if (username != null && jwtUtil.validation(authToken)){
            Claims allClaims = jwtUtil.getClaimsFromToken(authToken);
            List<String> roles = allClaims.get("role", List.class);
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(username,null, simpleGrantedAuthorities);
            return Mono.just(usernamePasswordAuthenticationToken);
        }else {
            return Mono.empty();
        }
    }
}
