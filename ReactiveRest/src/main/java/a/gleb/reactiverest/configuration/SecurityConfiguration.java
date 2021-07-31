package a.gleb.reactiverest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    private final SecurityContextRepository securityContextRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public SecurityConfiguration(SecurityContextRepository securityContextRepository, AuthenticationManager authenticationManager) {
        this.securityContextRepository = securityContextRepository;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(
                        (exchange, e) ->
                                Mono.fromRunnable(
                                        () -> exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
                                )
                )
                .accessDeniedHandler(
                        (exchange, e) ->
                                Mono.fromRunnable(
                                        () -> exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN)
                                )
                )
                .and()
                .csrf().disable()
                .formLogin().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/reactive_service/*", "/reactive_service/post/*", "/security/check/*", "/security/*", "/security/login").permitAll()
                .pathMatchers("/reactive_service/account/*", "/reactive_service/accounts", "/reactive_service/account").hasRole("ROLE_ADMINISTRATOR")
                .anyExchange().authenticated()
                .and()
                .build();
    }


}
