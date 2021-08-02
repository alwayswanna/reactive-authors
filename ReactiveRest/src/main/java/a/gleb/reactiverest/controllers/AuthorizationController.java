package a.gleb.reactiverest.controllers;

import a.gleb.reactiverest.configuration.JWTUtil;
import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.service.AccountWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/security/")
public class AuthorizationController {

    private final AccountWebClientService accountWebClientService;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    @Autowired
    public AuthorizationController(AccountWebClientService accountWebClientService, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.accountWebClientService = accountWebClientService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange exchange) {
        return exchange.getFormData()
                .flatMap(credentials ->
                        accountWebClientService.getAccountByUsername(credentials.getFirst("username"))
                                .cast(Account.class)
                                .map(userDetails ->
                                        {
                                            return passwordEncoder.matches(
                                                    credentials.getFirst("password"),
                                                    userDetails.getPassword()
                                            )
                                                    ? ResponseEntity.ok(jwtUtil.generateToken(userDetails))
                                                    : UNAUTHORIZED;
                                        }
                                )
                        .defaultIfEmpty(UNAUTHORIZED)
                );
    }
}
