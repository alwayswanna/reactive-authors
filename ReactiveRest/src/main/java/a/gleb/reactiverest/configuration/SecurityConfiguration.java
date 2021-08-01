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
                .pathMatchers("/security/login", "/unauthorized/create/account", "/unauthorized/posts", "/unauthorized/post/*").permitAll()
                .pathMatchers("/author_authorization/create/post", "/author_authorization/edit/post/*", "/author_authorization/delete/post/*",
                        "/author_authorization/edit/account/{id}", "/author_authorization/delete/account/{id}").hasRole("AUTHOR")
                .pathMatchers("/administrator_authorization/accounts").hasRole("ADMINISTRATOR")
                .anyExchange().authenticated()
                .and()
                .build();
    }


}
